package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.PhoneDetailsViewModel
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.ViewModelFactory

@Module
internal interface ViewModelModule {
    @Binds
    @IntoMap
    @KeyViewModel(PhoneDetailsViewModel::class)
    fun bindPhonesViewModel(viewModel: PhoneDetailsViewModel): ViewModel

    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}