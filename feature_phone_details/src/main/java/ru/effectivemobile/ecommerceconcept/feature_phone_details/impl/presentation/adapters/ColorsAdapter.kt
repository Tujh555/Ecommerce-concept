package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.effectivemobile.ecommerceconcept.feature_phone_details.databinding.ColorSelectListItemBinding
import javax.inject.Inject

internal class ColorsAdapter @Inject constructor() : OneSelectedItemAdapter<Int , ColorsAdapter.ColorViewHolder>(IntDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(
            ColorSelectListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ColorViewHolder(
        private val binding: ColorSelectListItemBinding
    ) : OneSelectedItemViewHolder<Int>(binding.root) {
        override fun bind(item: Int) {
            binding.root.backgroundTintList = ColorStateList.valueOf(item)

            binding.cbColor.isChecked = isItemChecked(bindingAdapterPosition)
        }

    }
}