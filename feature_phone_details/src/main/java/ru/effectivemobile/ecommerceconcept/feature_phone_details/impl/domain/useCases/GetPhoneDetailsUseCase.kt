package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.useCases

import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.PhoneDetails

internal interface GetPhoneDetailsUseCase {
    suspend operator fun invoke(): PhoneDetails
}