package ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain

import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData

internal interface Repository {
    suspend fun getHomePage(): HomePageData
}