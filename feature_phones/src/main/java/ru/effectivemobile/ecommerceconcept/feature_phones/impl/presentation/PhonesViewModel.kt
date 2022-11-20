package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FilterInteract
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhoneFilterData
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.FeaturePhonesComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.GetHomePageUseCase
import javax.inject.Inject

internal class PhonesViewModel @Inject constructor(
    private val getHomePageUseCase: GetHomePageUseCase
) : ViewModel(), FilterInteract {
    private val _phonesData = MutableStateFlow<Response<HomePageData>>(Response.Idle())
    val phonesData = _phonesData.asStateFlow()

    init {
        viewModelScope.launch {
            if (_phonesData.value is Response.Idle) return@launch

            _phonesData.emit(Response.Loading())

            val data = try {
                Response.Success(getHomePageUseCase())
            } catch (e: Exception) {
                Response.Failure(e)
            }

            _phonesData.emit(data)
        }
    }

    override fun onCleared() {
        super.onCleared()

        FeaturePhonesComponentHolder.reset()
    }

    override fun filter(filterData: PhoneFilterData) {
        if (_phonesData.value is Response.Success<HomePageData>) {
            (_phonesData.value as Response.Success).answer.let { homePageData ->
                _phonesData.value = Response.Success(homePageData.filter(filterData))
            }
        }
    }

    private fun HomePageData.filter(filterData: PhoneFilterData): HomePageData {
        val newBestSellerProducts = bestSellerProducts.filter {
            filterData.brand in it.title.lowercase() && it.priceWithDiscount.toInt() in filterData.priceRange
        }

        val newHotSales = homePageProducts.filter {
            filterData.brand in it.title.lowercase()
        }

        return copy(
            bestSellerProducts = newBestSellerProducts,
            homePageProducts = newHotSales
        )
    }
}