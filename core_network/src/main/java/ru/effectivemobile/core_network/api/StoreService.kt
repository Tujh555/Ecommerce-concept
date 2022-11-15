package ru.effectivemobile.core_network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.effectivemobile.core_network.Const

fun StoreApi(): StoreApi = Retrofit
    .Builder()
    .baseUrl(Const.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(StoreApi::class.java)