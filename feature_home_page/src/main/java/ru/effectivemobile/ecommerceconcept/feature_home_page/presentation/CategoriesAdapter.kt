package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.ProductCategoriesListItemSelectedBinding
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.ProductCategoriesListItemUnselectedBinding

internal class CategoriesAdapter : ListAdapter<Category, RecyclerView.ViewHolder>(DiffCallback()) {
    private var selectedItemPosition = 0
    private var lastSelectedItemPosition = -1

    var onCategoryClickListener: OnCategoryClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (position == selectedItemPosition) {
            SELECTED_ID
        } else {
            UNSELECTED_ID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            SELECTED_ID -> CategorySelectedViewHolder(
                ProductCategoriesListItemSelectedBinding.inflate(inflater, parent, false)
            )
            UNSELECTED_ID -> CategoryUnselectedViewHolder(
                ProductCategoriesListItemUnselectedBinding.inflate(inflater, parent, false)
            )
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.run {
            itemView.setOnClickListener {
                if (bindingAdapterPosition != selectedItemPosition) {
                    selectedItemPosition = bindingAdapterPosition

                    lastSelectedItemPosition = if (lastSelectedItemPosition == -1) {
                        selectedItemPosition
                    } else {
                        notifyItemChanged(lastSelectedItemPosition)
                        selectedItemPosition
                    }

                    onCategoryClickListener?.onClick(getItem(position).title)
                    notifyItemChanged(selectedItemPosition)
                }
            }
        }

        if (position == selectedItemPosition) {
            val newHolder = holder as CategorySelectedViewHolder
            newHolder.bind(getItem(position))
        } else {
            val newHolder = holder as CategoryUnselectedViewHolder
            newHolder.bind(getItem(position))
        }
    }

    inner class CategoryUnselectedViewHolder(
        private val binding: ProductCategoriesListItemUnselectedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.run {
                tvTitle.text = category.title
                val drawable = AppCompatResources.getDrawable(root.context, category.unselectedDrawableId)
                ivItemIcon.setImageDrawable(drawable)
            }
        }
    }

    inner class CategorySelectedViewHolder(
        private val binding: ProductCategoriesListItemSelectedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.run {
                tvTitle.text = category.title
                val drawable = AppCompatResources.getDrawable(root.context, category.selectedDrawableId)
                ivItemIcon.setImageDrawable(drawable)
            }
        }
    }

    fun interface OnCategoryClickListener {
        fun onClick(name: String)
    }

    private companion object {
        const val SELECTED_ID = 1
        const val UNSELECTED_ID = 2
    }
}