package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.core_network_impl.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.PhoneDetails
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.useCases.GetPhoneDetailsUseCase
import javax.inject.Inject

internal class PhoneDetailsViewModel @Inject constructor(
    private val getPhoneDetailsUseCase: GetPhoneDetailsUseCase
) : ViewModel() {
    private val _details = MutableStateFlow<Response<PhoneDetails>>(Response.Idle())
    val details = _details.asStateFlow()

    init {
        viewModelScope.launch {
            if (_details.value !is Response.Idle) return@launch

            _details.emit(Response.Loading())

            val data = try {
                Response.Success(getPhoneDetailsUseCase())
            } catch (e: Exception) {
                Response.Failure(e)
            }

            _details.emit(data)
        }
    }
}