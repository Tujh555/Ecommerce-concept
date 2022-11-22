package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal abstract class OneSelectedItemAdapter<T, VH : OneSelectedItemViewHolder<T>>(
    callback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(callback) {
    private var selectedItemPosition = 0
    private var lastSelectedItemPosition = 0

    protected open fun isItemChecked(position: Int) = selectedItemPosition == position

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.run {
            itemView.setOnClickListener {
                if (bindingAdapterPosition != selectedItemPosition) {
                    selectedItemPosition = bindingAdapterPosition

                    notifyItemChanged(lastSelectedItemPosition)
                    lastSelectedItemPosition = selectedItemPosition

                    notifyItemChanged(selectedItemPosition)
                }
            }
        }
        holder.bind(getItem(position))
    }
}