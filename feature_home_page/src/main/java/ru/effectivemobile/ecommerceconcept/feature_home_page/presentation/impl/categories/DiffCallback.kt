package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.categories

import androidx.recyclerview.widget.DiffUtil

internal class DiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

}