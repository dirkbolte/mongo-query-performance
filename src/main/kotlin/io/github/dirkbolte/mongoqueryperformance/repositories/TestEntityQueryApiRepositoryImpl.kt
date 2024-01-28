package io.github.dirkbolte.mongoqueryperformance.repositories

import com.mongodb.client.MongoClient
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntity
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntityConverter
import org.bson.Document
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class TestEntityQueryApiRepositoryImpl(
    private val mongoTemplate: MongoTemplate,
    private val mongoClient: MongoClient,
) : TestEntityQueryApiRepository {

    private val converter = CoreTestEntityConverter()

    override fun findAllWithQueryApi(): List<CoreTestEntity> {
        val query = Query(Criteria.where("_id").ne(null)).apply {
            fields().include("_id", "name", "otherParam")
        }
        return mongoTemplate.find(query, Document::class.java, "testCollection").map(converter::convert)
    }

    override fun findAllNative(): List<CoreTestEntity> {
        return mongoClient
            .getDatabase("test")
            .getCollection("testCollection")
            .find(Document.parse("""{ "_id": { ${"$"}ne: null } }"""))
            .projection(
                Document.parse(
                    """
                        {
                            "_id": 1,
                            "name": 1,
                            "otherParam": 1
                        }
                    """.trimIndent()
                )
            ).map(converter::convert)
            .toList()
    }
}