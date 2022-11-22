package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class OneSelectedItemViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}