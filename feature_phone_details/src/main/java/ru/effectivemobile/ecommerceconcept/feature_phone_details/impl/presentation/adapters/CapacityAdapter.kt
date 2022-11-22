package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ru.effectivemobile.ecommerceconcept.core_ui.R
import ru.effectivemobile.ecommerceconcept.feature_phone_details.databinding.CapacitySelectListItemBinding
import javax.inject.Inject

internal class CapacityAdapter @Inject constructor() :
    OneSelectedItemAdapter<String, CapacityAdapter.CapacityViewHolder>(StringDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapacityViewHolder {
        return CapacityViewHolder(
            CapacitySelectListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class CapacityViewHolder(
        private val binding: CapacitySelectListItemBinding
    ) : OneSelectedItemViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.tvCapacity.text = item

            binding.tvCapacity.run {
                if (isItemChecked(bindingAdapterPosition)) {
                    setBackgroundResource(CAPACITY_CHECKED_BACKGROUND)

                    setTextColor(
                        ContextCompat.getColor(binding.root.context, CAPACITY_CHECKED_COLOR)
                    )
                } else {
                    setBackgroundResource(0)
                    setTextColor(
                        ContextCompat.getColor(binding.root.context, CAPACITY_UNCHECKED_COLOR)
                    )
                }
            }
        }
    }

    private companion object {
        val CAPACITY_CHECKED_COLOR = R.color.capacity_checked
        val CAPACITY_UNCHECKED_COLOR = R.color.capacity_unchecked
        val CAPACITY_CHECKED_BACKGROUND = R.drawable.capacity_item_background
    }
}