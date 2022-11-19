package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules

import dagger.Binds
import dagger.Module
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.data.RepositoryImpl
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.PhonesScope
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.Repository

@Module
internal interface RepositoryModule {
    @Binds
    @PhonesScope
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}