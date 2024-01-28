package io.github.dirkbolte.mongoqueryperformance

import io.github.dirkbolte.mongoqueryperformance.configuration.MongoConfig
import io.github.dirkbolte.mongoqueryperformance.repositories.TestEntityRepository
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.NestedEntity
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.TestEntity
import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import kotlin.system.measureTimeMillis


@DataMongoTest
@Import(MongoConfig::class)
@Execution(value = ExecutionMode.SAME_THREAD)
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
abstract class RepositoryIntegrationTest {

    @Autowired
    private lateinit var initRepository: TestEntityRepository

    companion object {

        private const val RUNS = 100
        const val DOCUMENTS = 20_000

        @Container
        val mongoDB = MongoDBContainer("mongo:6").apply {
            waitingFor(Wait.forListeningPort())
            start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicMongoDbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongoDB.replicaSetUrl }
        }

    }

    @BeforeAll
    fun initializeDatabase() {
        initRepository.deleteAll()
        repeat(DOCUMENTS) {
            initRepository.save(
                TestEntity(
                    name = "name-${RandomStringUtils.randomAlphabetic(10)}",
                    otherParam = "other-${RandomStringUtils.randomAlphabetic(10)}",
                    nested = (0..30).map { NestedEntity() }
                )
            )
        }
        initRepository.findAll()
    }

    protected fun measure(name: String, block: () -> Unit) {
        val measurements = (0..RUNS).map {
            measureTimeMillis { block() }
        }
        println("$name: ${measurements.joinToString(",")}")
        println("${name}: min: ${measurements.min()} max: ${measurements.max()}  average: ${measurements.sum()/ RUNS}")
    }

}