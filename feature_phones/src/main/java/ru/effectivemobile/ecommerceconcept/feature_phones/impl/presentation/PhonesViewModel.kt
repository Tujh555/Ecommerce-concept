package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.core_network_impl.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhoneFilterData
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.FeaturePhonesComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.GetHomePageUseCase
import javax.inject.Inject

internal class PhonesViewModel @Inject constructor(
    private val getHomePageUseCase: GetHomePageUseCase
) : ViewModel() {
    private val _phonesData = MutableStateFlow<Response<HomePageData>>(Response.Idle())
    val phonesData = _phonesData.asStateFlow()

    fun startLoading(filterData: PhoneFilterData?) {
        viewModelScope.launch {
            if (_phonesData.value !is Response.Idle) return@launch

            _phonesData.emit(Response.Loading())

            val data = try {
                val response = getHomePageUseCase()
                if (filterData != null) {
                    Response.Success(response.filter(filterData))
                } else {
                    Response.Success(response)
                }
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

    private fun HomePageData.filter(filterData: PhoneFilterData): HomePageData {
        val newBestSellerProducts = bestSellerProducts.filter {
            filterData.brand.lowercase() in it.title.lowercase()
                    && it.priceWithDiscount.toInt() in (filterData.priceBottom..filterData.priceTop)
        }

        val newHotSales = homePageProducts.filter {
            filterData.brand.lowercase() in it.title.lowercase()
        }

        return copy(
            bestSellerProducts = newBestSellerProducts,
            homePageProducts = newHotSales
        )
    }
}