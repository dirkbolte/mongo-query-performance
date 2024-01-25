package io.github.dirkbolte.mongoqueryperformance.repositories

import io.github.dirkbolte.mongoqueryperformance.RepositoryIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class TestEntityRepositoryTest : RepositoryIntegrationTest() {

    @Autowired
    private lateinit var repository: TestEntityRepository

    @Autowired
    private lateinit var coreRepository: CoreTestEntityRepository


    @Test
    fun `full entity`() {
        measure("full entity") { assertThat(repository.findAll()).hasSize(20_000) }
    }

    @Test
    fun `test interface auto projection`() {
        measure("auto project interface") { assertThat(repository.findAllByIdNotNull()).hasSize(20_000) }
        assertThat(repository.findAllByIdNotNull()).hasSize(20_000)
    }

    @Test
    fun `test interface manual projection`() {
        measure("manual project interface") { assertThat(repository.findAllCoreInterface()).hasSize(20_000) }
        assertThat(repository.findAllCoreInterface()).hasSize(20_000)
    }

    @Test
    fun `small test entity without converter`() {
        measure("small entity without converter") { assertThat(repository.findAllCoreEntityNoConverter()).hasSize(20_000) }
        assertThat(repository.findAllCoreEntityNoConverter()).hasSize(20_000)
    }


    @Test
    fun `small test entity with converter without dedicated repository`() {
        measure("small entity with converter and no dedicated repository") { assertThat(repository.findAllCoreEntityConverter()).hasSize(20_000) }
        assertThat(repository.findAllCoreEntityConverter()).hasSize(20_000)
    }

    @Test
    fun `small test entity with converter with dedicated repository`() {
        measure("small entity with converter and dedicated repository") { assertThat(coreRepository.findAllCoreEntity()).hasSize(20_000) }
        assertThat(coreRepository.findAllCoreEntity()).hasSize(20_000)
    }

    @Test
    fun `with query API`() {
        measure("with query API") { assertThat(repository.findAllWithQueryApi()).hasSize(20_000) }
        assertThat(repository.findAllWithQueryApi()).hasSize(20_000)
    }

    @Test
    fun `native mongoClient`() {
        measure("native mongoClient") { assertThat(repository.findAllNative()).hasSize(20_000) }
        assertThat(repository.findAllNative()).hasSize(20_000)
    }
}