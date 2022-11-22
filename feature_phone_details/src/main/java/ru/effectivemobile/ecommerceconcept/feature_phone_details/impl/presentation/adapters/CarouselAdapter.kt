package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.effectivemobile.ecommerceconcept.feature_phone_details.databinding.CarouselItemBinding
import javax.inject.Inject

internal class CarouselAdapter @Inject constructor()
    : ListAdapter<String, CarouselAdapter.CarouselViewHolder>(StringDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            CarouselItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(imageUrl = getItem(position))
    }

    inner class CarouselViewHolder(
        private val binding: CarouselItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.run {
                Glide.with(root)
                    .load(imageUrl)
                    .centerCrop()
                    .into(ivImage)
            }
        }
    }
}