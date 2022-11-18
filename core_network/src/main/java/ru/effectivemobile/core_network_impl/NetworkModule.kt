package ru.effectivemobile.core_network_impl

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.effectivemobile.core_network_api.StoreService
import javax.inject.Singleton

@Module
internal class NetworkModule {
    @Singleton
    @Provides
    fun provideStoreService(): StoreService = Retrofit
        .Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StoreService::class.java)
}