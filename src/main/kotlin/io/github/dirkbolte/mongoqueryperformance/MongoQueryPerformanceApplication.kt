package io.github.dirkbolte.mongoqueryperformance

import io.github.dirkbolte.mongoqueryperformance.configuration.MongoConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(scanBasePackageClasses = [MongoConfig::class])
class MongoQueryPerformanceApplication

fun main(args: Array<String>) {
	runApplication<MongoQueryPerformanceApplication>(*args)
}
