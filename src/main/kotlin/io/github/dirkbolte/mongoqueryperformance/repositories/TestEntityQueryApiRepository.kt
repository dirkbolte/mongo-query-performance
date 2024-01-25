package io.github.dirkbolte.mongoqueryperformance.repositories

import io.github.dirkbolte.mongoqueryperformance.repositories.entities.CoreTestEntity

interface TestEntityQueryApiRepository {

    fun findAllWithQueryApi(): List<CoreTestEntity>
    fun findAllNative(): List<CoreTestEntity>

}
