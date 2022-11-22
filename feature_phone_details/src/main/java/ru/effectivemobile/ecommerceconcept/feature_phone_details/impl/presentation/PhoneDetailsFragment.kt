package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import ru.effectivemobile.core_network_impl.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phone_details.R
import ru.effectivemobile.ecommerceconcept.feature_phone_details.databinding.CarouselItemBinding
import ru.effectivemobile.ecommerceconcept.feature_phone_details.databinding.FragmentPhoneDetailsBinding
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.FeaturePhoneDetailsDependenciesProvider
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.PhoneDetailsComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters.CarouselAdapter
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.adapters.MainInfoAdapter
import javax.inject.Inject
import kotlin.math.abs

internal class PhoneDetailsFragment : Fragment(R.layout.fragment_phone_details) {
    private val binding by viewBinding(FragmentPhoneDetailsBinding::bind)
    private val mainInfoItems by lazy {
        resources.getStringArray(R.array.main_items)
    }

    @Inject
    lateinit var mainAdapter: MainInfoAdapter

    @Inject
    lateinit var carouselAdapter: CarouselAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PhoneDetailsViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        PhoneDetailsComponentHolder.init(FeaturePhoneDetailsDependenciesProvider.dependencies)
        PhoneDetailsComponentHolder.phoneComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFlows()
        initUi()
    }

    private fun initUi() {
        binding.vpMain.adapter = mainAdapter

        TabLayoutMediator(binding.tlProductActions,binding.vpMain) { tab, position ->
            tab.text = mainInfoItems[position]
        }.attach()

        binding.vpImages.run {
            offscreenPageLimit = 1
            adapter = carouselAdapter

            val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * abs(position))
            }
            setPageTransformer(pageTransformer)

            val itemDecoration = HorizontalMarginItemDecoration(
                context,
                R.dimen.viewpager_current_item_horizontal_margin
            )
            addItemDecoration(itemDecoration)
        }
    }

    private fun observeFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.details.collect {
                when (it) {
                    is Response.Failure -> Toast.makeText(
                        requireContext(),
                        resources.getString(R.string.failure_text),
                        Toast.LENGTH_SHORT
                    ).show()

                    is Response.Idle -> {}

                    is Response.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE

                        binding.run {
                            tvProductName.text = it.data.title
                            ratingBar.rating = it.data.rating
                            cbIsInFavorites.isChecked = it.data.isFavorites
                        }

                        carouselAdapter.submitList(it.data.images)
                        mainAdapter.applyShopData(it.data)
                    }
                }
            }
        }
    }
}