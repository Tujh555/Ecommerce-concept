package ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases

import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData

internal interface GetHomePageUseCase {
    suspend operator fun invoke(): HomePageData
}