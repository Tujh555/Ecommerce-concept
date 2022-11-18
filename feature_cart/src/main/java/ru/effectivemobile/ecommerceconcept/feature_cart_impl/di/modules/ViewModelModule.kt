package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation.CartViewModel
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation.ViewModelFactory

@Module
internal interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    fun bindFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}