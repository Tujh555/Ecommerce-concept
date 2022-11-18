package ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation

import androidx.recyclerview.widget.DiffUtil
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.CartProduct
import javax.inject.Inject

internal class CartItemDiffCallback @Inject constructor() : DiffUtil.ItemCallback<CartProduct>() {
    override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
        return oldItem == newItem
    }
}