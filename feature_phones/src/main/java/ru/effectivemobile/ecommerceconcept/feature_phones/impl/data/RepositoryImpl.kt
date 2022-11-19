package ru.effectivemobile.ecommerceconcept.feature_phones.impl.data

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.Repository
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val service: StoreService
) : Repository {
    override suspend fun getHomePage(): HomePageData {
        return service.getHomePage().toDomain()
    }
}