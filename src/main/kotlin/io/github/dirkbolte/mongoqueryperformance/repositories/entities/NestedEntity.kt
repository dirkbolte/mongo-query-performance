package io.github.dirkbolte.mongoqueryperformance.repositories.entities

import org.apache.commons.lang3.RandomStringUtils
import java.util.Random

data class NestedEntity(
    val nestedParamOne: String = RandomStringUtils.randomAlphanumeric(10),
    val nestedParamTwp: String = RandomStringUtils.randomAlphanumeric(10),
    val nestedParamThree: String = RandomStringUtils.randomAlphanumeric(10),
    val nestedParamFour: String = RandomStringUtils.randomAlphanumeric(10),
    val nestedParamFive: String = RandomStringUtils.randomAlphanumeric(10),
)
