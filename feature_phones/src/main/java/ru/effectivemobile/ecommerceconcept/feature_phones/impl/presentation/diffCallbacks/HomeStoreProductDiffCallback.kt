package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomeStoreProduct
import javax.inject.Inject

internal class HomeStoreProductDiffCallback @Inject constructor() : DiffUtil.ItemCallback<HomeStoreProduct>() {
    override fun areItemsTheSame(oldItem: HomeStoreProduct, newItem: HomeStoreProduct): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HomeStoreProduct, newItem: HomeStoreProduct): Boolean {
        return oldItem == newItem
    }
}