package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.effectivemobile.ecommerceconcept.feature_phones.R
import ru.effectivemobile.ecommerceconcept.feature_phones.databinding.BestSellerListItemBinding
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.BestSellerProduct
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.diffCallbacks.BestSellerDiffCallback
import java.util.*
import javax.inject.Inject

internal class BestSellerAdapter @Inject constructor(
    callback: BestSellerDiffCallback
) : ListAdapter<BestSellerProduct, BestSellerAdapter.BestSellerViewHolder>(callback) {
    var phoneClickListener: OnPhoneClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        return BestSellerViewHolder(
            BestSellerListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class BestSellerViewHolder(
        private val binding: BestSellerListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BestSellerProduct) {
            binding.run {
                tvTitle.text = item.title
                tvActualPrice.text = root.context
                    .getString(
                        R.string.price,
                        String.format(Locale.US, "%,d", item.priceWithDiscount.toInt())
                    )
                tvOldPrice.text = root.context
                    .getString(
                        R.string.strikethrough_price,
                        String.format(Locale.US, "%,d", item.priceWithoutDiscount.toInt())
                    )
                ivIsInFavorites.isChecked = item.isFavorites

                ivIsInFavorites.setOnClickListener {
                    item.isFavorites = !item.isFavorites
                }

                Glide.with(root)
                    .load(item.pictureUrl)
                    .centerCrop()
                    .into(binding.ivImage)

                root.setOnClickListener { phoneClickListener?.click() }
            }
        }
    }
}