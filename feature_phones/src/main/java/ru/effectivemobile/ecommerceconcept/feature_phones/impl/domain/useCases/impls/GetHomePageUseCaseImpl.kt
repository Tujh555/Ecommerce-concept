package ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.impls

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.Repository
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.GetHomePageUseCase
import javax.inject.Inject

internal class GetHomePageUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetHomePageUseCase {
    override suspend fun invoke(): HomePageData = withContext(Dispatchers.IO) {
        repository.getHomePage()
    }
}