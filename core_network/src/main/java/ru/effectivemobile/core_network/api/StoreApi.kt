package ru.effectivemobile.core_network.api

import retrofit2.http.GET
import ru.effectivemobile.core_network.entities.Cart
import ru.effectivemobile.core_network.entities.DetailedProduct
import ru.effectivemobile.core_network.entities.HomePageData

interface StoreApi {
    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHomePage(): HomePageData

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getProductById(id: String = ""): DetailedProduct

    @GET("6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getCart(userId: String = ""): Cart
}