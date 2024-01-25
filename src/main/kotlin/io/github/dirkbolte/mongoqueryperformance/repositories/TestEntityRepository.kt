package io.github.dirkbolte.mongoqueryperformance.repositories

import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntity
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntityInterface
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntityNoConverter
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.TestEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface TestEntityRepository : MongoRepository<TestEntity, String>, TestEntityQueryApiRepository {

    fun findAllByIdNotNull(): List<CoreTestEntityInterface>

    @Query(
        value = "{}",
        fields = """{"_id":  1, "name":  1, "otherParam":  1}"""
    )
    fun findAllCoreInterface(): List<CoreTestEntityInterface>

    @Query(
        value = "{}",
        fields = """{"_id":  1, "name":  1, "otherParam":  1}"""
    )
    fun findAllCoreEntityNoConverter(): List<CoreTestEntityNoConverter>

    @Query(
        value = "{}",
        fields = """{"_id":  1, "name":  1, "otherParam":  1}"""
    )
    fun findAllCoreEntityConverter(): List<CoreTestEntity>
}