package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import ru.effectivemobile.ecommerceconcept.feature_phones.databinding.HotSalesListItemBinding
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomeStoreProduct
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.diffCallbacks.HomeStoreProductDiffCallback
import javax.inject.Inject

internal class HotSalesAdapter @Inject constructor(
    callback: HomeStoreProductDiffCallback
) : ListAdapter<HomeStoreProduct, HotSalesAdapter.HotSalesViewHolder>(callback) {
    var phoneClickListener: OnPhoneClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return HotSalesViewHolder(
            HotSalesListItemBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HotSalesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HotSalesViewHolder(
        private val binding: HotSalesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeStoreProduct) {
            binding.run {
                cvButNow.visibility = if (item.isBuy) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }

                tvIsNew.visibility = if (item.isNew) {
                    View.VISIBLE
                } else {
                    View.INVISIBLE
                }

                tvTitle.text = item.title
                tvSubtitle.text = item.subtitle

                root.setOnClickListener { phoneClickListener?.click() }

                Glide.with(root)
                    .load(item.pictureUrl)
                    .into(ivImage)
            }
        }
    }
}