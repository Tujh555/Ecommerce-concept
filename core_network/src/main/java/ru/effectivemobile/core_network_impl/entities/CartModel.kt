package ru.effectivemobile.core_network_impl.entities

data class CartModel(
   var basket: List<BasketProductModel> = emptyList(),
   var delivery: String = "",
   var id: String = "",
   var total: Float = 0.0f
)