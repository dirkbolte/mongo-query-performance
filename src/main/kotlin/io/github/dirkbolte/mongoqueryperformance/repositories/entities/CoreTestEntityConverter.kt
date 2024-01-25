package io.github.dirkbolte.mongoqueryperformance.repositories.entities

import org.bson.Document
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter

@ReadingConverter
class CoreTestEntityConverter : Converter<Document, CoreTestEntity> {
    override fun convert(source: Document): CoreTestEntity {
        return CoreTestEntity(
            source.getString("_id"),
            source.getString("name"),
            source.getString("otherParam"),
        )
    }
}