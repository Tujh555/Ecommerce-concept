package ru.effectivemobile.core_network_api

import retrofit2.http.GET
import ru.effectivemobile.core_network_impl.entities.CartModel
import ru.effectivemobile.core_network_impl.entities.DetailedProductModel
import ru.effectivemobile.core_network_impl.entities.HomePageDataModel

interface StoreService {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomePage(): HomePageDataModel

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductById(): DetailedProductModel

    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): CartModel
}