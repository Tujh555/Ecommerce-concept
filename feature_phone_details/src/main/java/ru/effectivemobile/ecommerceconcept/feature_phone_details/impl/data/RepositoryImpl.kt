package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.data

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.PhoneDetails
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.Repository
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(private val service: StoreService) : Repository {
    override suspend fun getPhoneDetails(): PhoneDetails {
        return service.getDetailedProduct().toDomain()
    }
}