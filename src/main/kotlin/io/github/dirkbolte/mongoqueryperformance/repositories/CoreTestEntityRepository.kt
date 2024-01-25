package io.github.dirkbolte.mongoqueryperformance.repositories

import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntity
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.TestEntity
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntityInterface
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface CoreTestEntityRepository : MongoRepository<CoreTestEntity, String> {

    @Query(value = "{}",
        fields = """{"_id":  1, "name":  1, "otherParam":  1}""")
    fun findAllCoreEntity(): List<CoreTestEntity>
}