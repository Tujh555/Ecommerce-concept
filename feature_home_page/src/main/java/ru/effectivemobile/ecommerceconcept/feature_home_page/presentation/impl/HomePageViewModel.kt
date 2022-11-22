package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di.HomePageComponentHolder

internal class HomePageViewModel : ViewModel() {
    private val _cartCount = MutableLiveData<Int>()
    val cartCount: LiveData<Int>
        get() = _cartCount

    fun loadCartCount() {
        HomePageComponentHolder.cartCountProvider?.let {
            viewModelScope.launch {
                _cartCount.postValue(
                    try {
                        it.getProductCount()
                    } catch (e: Exception) {
                        0
                    }
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        HomePageComponentHolder.reset()
    }
}