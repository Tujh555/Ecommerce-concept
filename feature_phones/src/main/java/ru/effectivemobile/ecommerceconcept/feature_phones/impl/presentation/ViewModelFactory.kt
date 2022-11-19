package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
internal class ViewModelFactory @Inject constructor(
    private val providers: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]?.get() as? T
            ?: throw IllegalArgumentException("Unknown view model type $modelClass")
    }
}