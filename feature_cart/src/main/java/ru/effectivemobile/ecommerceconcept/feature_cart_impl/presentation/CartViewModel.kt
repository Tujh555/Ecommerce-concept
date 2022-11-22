package ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.TAG
import ru.effectivemobile.core_network_impl.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.Cart
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.CartProduct
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.useCases.GetUserCartUseCase
import javax.inject.Inject

internal class CartViewModel @Inject constructor(
    private val getUserCartUseCase: GetUserCartUseCase
) : ViewModel() {
    private val _cartData = MutableStateFlow<Response<Cart.OwnCartData>>(Response.Idle())
    val cartData = _cartData.asStateFlow()

    private val _cartProducts = MutableStateFlow(listOf<CartProduct>())
    val cartProducts = _cartProducts.asStateFlow()

    private var cartProductsList = mutableListOf<CartProduct>()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, "unhandled $throwable in $coroutineContext")

        viewModelScope.launch {
            _cartData.emit(Response.Failure(IllegalStateException()))
        }
    }

    override fun onCleared() {
        super.onCleared()
        CartFeatureComponentHolder.reset()
    }

    fun loadUserCart(id: String = "") {
        if (_cartData.value !is Response.Idle) return

        viewModelScope.launch(exceptionHandler) {
            _cartData.emit(Response.Loading())

            val cart = try {
                getUserCartUseCase()
            } catch (e: Exception) {
                _cartData.emit(Response.Failure(e))
                e.printStackTrace()
                return@launch
            }

            _cartData.emit(Response.Success(cart.ownData))
            _cartProducts.emit(cart.basket)

            cartProductsList.addAll(cart.basket)
        }
    }

    fun deleteItem(item: CartProduct) {
        val oldData = _cartData.value as? Response.Success ?: return

        _cartData.value = Response.Success(
            oldData
                .data
                .copy(
                    total = oldData.data.total - item.price
                )
        )
    }

    fun takeOutItem(item: CartProduct) {
        cartProductsList.remove(item)

        viewModelScope.launch {
            _cartProducts.emit(cartProductsList.toList())
        }

        val newCartData = (cartData.value as? Response.Success)?.let {
            it.data.copy(
                total = it.data.total - item.price * item.countInCart
            )
        } ?: return

        _cartData.value = Response.Success(newCartData)
    }

    fun addItem(item: CartProduct) {
        val oldData = _cartData.value as? Response.Success ?: return

        _cartData.value = Response.Success(
            oldData
                .data
                .copy(
                    total = oldData.data.total + item.price
                )
        )
    }
}