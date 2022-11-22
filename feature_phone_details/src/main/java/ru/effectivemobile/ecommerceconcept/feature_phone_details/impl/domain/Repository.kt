package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain

internal interface Repository {
    suspend fun getPhoneDetails(): PhoneDetails
}