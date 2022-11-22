package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.effectivemobile.ecommerceconcept.feature_phone_details.R
import ru.effectivemobile.ecommerceconcept.feature_phone_details.databinding.ShopLayoutBinding
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.PhoneDetails
import java.util.*
import javax.inject.Inject

internal class MainInfoAdapter @Inject constructor(
    private val colorsAdapter: ColorsAdapter,
    private val capacityAdapter: CapacityAdapter
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var shopData: PhoneDetails? = null

    fun applyShopData(details: PhoneDetails) {
        shopData = details
        notifyItemChanged(0)
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        SHOP_ID
    } else {
        NOT_IMPLEMENTED_ID
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == SHOP_ID) {
            ShopViewHolder(
                ShopLayoutBinding.inflate(inflater, parent, false)
            )
        } else {
            NotImplementedHolder(
                inflater.inflate(R.layout.fragment_not_implemented, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            shopData?.let {
                (holder as ShopViewHolder).bind(it)
            }
        }
    }

    override fun getItemCount(): Int = 3

    inner class ShopViewHolder(
        private val binding: ShopLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(details: PhoneDetails) {
            binding.run {
                tvProcessor.text = details.cpu
                tvCamera.text = details.camera
                tvRam.text = details.ssd
                tvRom.text = details.sd

                tvPrice.text = root.context.getString(
                    R.string.text_price,
                    String.format(Locale.US, "%,.2f", details.price)
                )

                rvCapacity.adapter = capacityAdapter
                rvColors.adapter = colorsAdapter

                capacityAdapter.submitList(details.capacity)
                colorsAdapter.submitList(details.color)
            }
        }
    }

    class NotImplementedHolder(view: View) : RecyclerView.ViewHolder(view)

    private companion object {
        const val SHOP_ID = 1
        const val NOT_IMPLEMENTED_ID = 2
    }
}