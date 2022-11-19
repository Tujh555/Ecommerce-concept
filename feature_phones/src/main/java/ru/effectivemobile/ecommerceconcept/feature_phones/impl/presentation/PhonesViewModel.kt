package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.FeaturePhonesComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.useCases.GetHomePageUseCase
import javax.inject.Inject

internal class PhonesViewModel @Inject constructor(
    private val getHomePageUseCase: GetHomePageUseCase
) : ViewModel() {
    val phonesData = liveData {
        emit(Response.Loading())

        val data = try {
            Response.Success(getHomePageUseCase())
        } catch (e: Exception) {
            Response.Failure(e)
        }

        emit(data)
    }

    override fun onCleared() {
        super.onCleared()

        FeaturePhonesComponentHolder.reset()
    }
}