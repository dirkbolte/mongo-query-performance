package io.github.dirkbolte.mongoqueryperformance.repositories.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.util.UUID

@Document(collection = "testCollection")
data class CoreTestEntity(
    @field:MongoId(FieldType.STRING)
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val otherParam: String
)
