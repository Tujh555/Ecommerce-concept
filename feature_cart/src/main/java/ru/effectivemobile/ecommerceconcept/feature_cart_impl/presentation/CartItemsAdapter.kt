package ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.effectivemobile.ecommerceconcept.feature_cart.R
import ru.effectivemobile.ecommerceconcept.feature_cart.databinding.CartListItemBinding
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.CartProduct
import javax.inject.Inject

internal typealias OnItemClickListener = (CartProduct) -> Unit

internal class CartItemsAdapter @Inject constructor(
    callback: CartItemDiffCallback
) : ListAdapter<CartProduct, CartItemsAdapter.CartItemViewHolder>(callback) {
    var onItemPlus: OnItemClickListener? = null
    var onItemMinus: OnItemClickListener? = null
    var onItemDelete: OnItemClickListener? = null

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartItemViewHolder(
            CartListItemBinding.inflate(inflater, parent, false)
        )
    }

    inner class CartItemViewHolder(private val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartProduct) {
            binding.run {
                tvProductName.text = item.title
                tvPrice.text = root.context.getString(R.string.price_text, item.price)
                tvCountInCart.text = item.countInCart.toString()

                ivMinus.setOnClickListener {
                    val product = getItem(bindingAdapterPosition)

                    if (product.countInCart > 0) onItemMinus?.invoke(product)

                    product.countInCart -= 1
                    this@CartItemsAdapter.notifyItemChanged(
                        bindingAdapterPosition
                    )
                }

                ivPlus.setOnClickListener {
                    val product = getItem(bindingAdapterPosition)
                    product.countInCart += 1

                    onItemPlus?.invoke(product)

                    this@CartItemsAdapter.notifyItemChanged(
                        bindingAdapterPosition
                    )
                }

                ivDumpster.setOnClickListener {
                    onItemDelete?.invoke(getItem(bindingAdapterPosition))
                }

                Glide.with(root)
                    .load(item.imageUrl)
                    .centerCrop()
                    .into(ivProductImage)
            }
        }
    }
}