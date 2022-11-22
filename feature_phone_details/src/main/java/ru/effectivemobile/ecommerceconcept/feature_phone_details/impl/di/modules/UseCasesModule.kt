package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.modules

import dagger.Binds
import dagger.Module
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.data.RepositoryImpl
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.Repository
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.useCases.GetPhoneDetailsUseCase
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.useCases.impls.GetPhoneDetailsUseCaseImpl

@Module
internal interface UseCasesModule {
    @Binds
    fun bindPhoneDetailsUseCase(useCaseImpl: GetPhoneDetailsUseCaseImpl): GetPhoneDetailsUseCase

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}