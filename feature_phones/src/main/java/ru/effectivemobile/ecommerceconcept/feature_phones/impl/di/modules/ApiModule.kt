package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesApi
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.PhonesViewModel

@Module
internal interface ApiModule {
    @Binds
    fun bindApi(viewModel: PhonesViewModel): FeaturePhonesApi
}