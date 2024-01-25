package io.github.dirkbolte.mongoqueryperformance.configuration

import com.mongodb.client.MongoClient
import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntityConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions.MongoConverterConfigurationAdapter

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName() = "test"

    @Bean
    override fun mongoClient(): MongoClient {
        return super.mongoClient()
    }

    override fun configureConverters(adapter: MongoConverterConfigurationAdapter) {
        adapter.registerConverter(CoreTestEntityConverter())
    }
}