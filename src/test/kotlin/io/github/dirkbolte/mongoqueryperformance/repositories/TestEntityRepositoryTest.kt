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
        measure("full entity") { assertThat(repository.findAllCustom()).hasSize(DOCUMENTS) }
    }

    @Test
    fun `test interface auto projection`() {
        measure("auto project interface") { assertThat(repository.findAllByIdNotNull()).hasSize(DOCUMENTS) }
        assertThat(repository.findAllByIdNotNull()).hasSize(DOCUMENTS)
    }

    @Test
    fun `test interface manual projection`() {
        measure("manual project interface") { assertThat(repository.findAllCoreInterface()).hasSize(DOCUMENTS) }
        assertThat(repository.findAllCoreInterface()).hasSize(DOCUMENTS)
    }

    @Test
    fun `small test entity without converter`() {
        measure("small entity without converter") { assertThat(repository.findAllCoreEntityNoConverter()).hasSize(DOCUMENTS) }
        assertThat(repository.findAllCoreEntityNoConverter()).hasSize(DOCUMENTS)
    }


    @Test
    fun `small test entity with converter without dedicated repository`() {
        measure("small entity with converter and no dedicated repository") { assertThat(repository.findAllCoreEntityConverter()).hasSize(DOCUMENTS) }
        assertThat(repository.findAllCoreEntityConverter()).hasSize(DOCUMENTS)
    }

    @Test
    fun `small test entity with converter with dedicated repository`() {
        measure("small entity with converter and dedicated repository") { assertThat(coreRepository.findAllCoreEntity()).hasSize(DOCUMENTS) }
        assertThat(coreRepository.findAllCoreEntity()).hasSize(DOCUMENTS)
    }

    @Test
    fun `with query API`() {
        measure("with query API") { assertThat(repository.findAllWithQueryApi()).hasSize(DOCUMENTS) }
        assertThat(repository.findAllWithQueryApi()).hasSize(DOCUMENTS)
    }

    @Test
    fun `native mongoClient`() {
        measure("native mongoClient") { assertThat(repository.findAllNative()).hasSize(DOCUMENTS) }
        assertThat(repository.findAllNative()).hasSize(DOCUMENTS)
    }
}