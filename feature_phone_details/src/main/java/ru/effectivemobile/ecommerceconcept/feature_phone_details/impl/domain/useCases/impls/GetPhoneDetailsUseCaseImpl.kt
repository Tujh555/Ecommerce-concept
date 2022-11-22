package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.useCases.impls

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.PhoneDetails
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.Repository
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.useCases.GetPhoneDetailsUseCase
import javax.inject.Inject

internal class GetPhoneDetailsUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetPhoneDetailsUseCase {
    override suspend fun invoke(): PhoneDetails = withContext(Dispatchers.IO) {
        repository.getPhoneDetails()
    }
}