package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules

import dagger.Binds
import dagger.Module
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.GetHomePageUseCase
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.impls.GetHomePageUseCaseImpl

@Module
internal interface UseCasesModule {
    @Binds
    fun bindGetHomePageUseCase(useCaseImpl: GetHomePageUseCaseImpl): GetHomePageUseCase
}