package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.effectivemobile.ecommerceconcept.feature_phones.R
import ru.effectivemobile.ecommerceconcept.feature_phones.databinding.FragmentPhonesBinding
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.FeaturePhonesComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.PhonesDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.adapters.BestSellerAdapter
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.adapters.HotSalesAdapter
import javax.inject.Inject

internal class PhonesFragment : Fragment(R.layout.fragment_phones) {
    private val binding by viewBinding(FragmentPhonesBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var hotSalesAdapter: HotSalesAdapter

    @Inject
    lateinit var bestSellerAdapter: BestSellerAdapter

    private val viewModel by viewModels<PhonesViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        FeaturePhonesComponentHolder.init(PhonesDependencyProvider.dependencies)
        FeaturePhonesComponentHolder.featureComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            vpHotSales.setPageTransformer(Transformer())
            vpHotSales.adapter = hotSalesAdapter

            rvBestSeller.adapter = bestSellerAdapter
        }

        viewModel.phonesData.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Failure -> Toast.makeText(requireContext(), "Failure", Toast.LENGTH_SHORT).show()
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    hotSalesAdapter.submitList(it.answer.homePageProducts)
                    bestSellerAdapter.submitList(it.answer.bestSellerProducts)

                    Log.d("MyLogs", "Count ${bestSellerAdapter.currentList.size}")
                }
            }
        }
    }
}