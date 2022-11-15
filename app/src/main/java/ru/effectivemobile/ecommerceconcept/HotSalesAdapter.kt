package ru.effectivemobile.ecommerceconcept

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.effectivemobile.ecommerceconcept.databinding.HotSalesListItemBinding

private val COMPARATOR = object : DiffUtil.ItemCallback<MobileData>() {
    override fun areItemsTheSame(oldItem: MobileData, newItem: MobileData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MobileData, newItem: MobileData): Boolean {
        return oldItem == newItem
    }

}

class HotSalesAdapter : ListAdapter<MobileData, HotSalesAdapter.HotSalesViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HotSalesViewHolder(
            HotSalesListItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HotSalesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HotSalesViewHolder(private val binding: HotSalesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MobileData) {
            binding.run {
                tvTitle.text = data.title
                tvSubtitle.text = data.subtitle
                root.setOnClickListener {
                    it.context.toast("View #${data.id}")
                }

                Glide.with(root)
                    .load(data.pictureUrl)
                    .centerCrop()
                    .placeholder(R.drawable.test)
                    .into(ivImage)
            }
        }
    }
}