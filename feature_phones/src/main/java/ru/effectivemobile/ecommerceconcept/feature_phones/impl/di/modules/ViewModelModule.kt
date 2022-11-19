package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.PhonesViewModel
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.ViewModelFactory

@Module
internal interface ViewModelModule {
    @Binds
    @IntoMap
    @KeyViewModel(PhonesViewModel::class)
    fun bindPhonesViewModel(viewModel: PhonesViewModel): ViewModel

    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}