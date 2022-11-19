package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.BestSellerProduct
import javax.inject.Inject

internal class BestSellerDiffCallback @Inject constructor() : DiffUtil.ItemCallback<BestSellerProduct>() {
    override fun areItemsTheSame(oldItem: BestSellerProduct, newItem: BestSellerProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: BestSellerProduct,
        newItem: BestSellerProduct
    ): Boolean {
        return oldItem == newItem
    }
}