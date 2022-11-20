package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import ru.effectivemobile.ecommerceconcept.feature_phones.R
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhoneFilterData
import ru.effectivemobile.ecommerceconcept.feature_phones.databinding.FragmentPhonesBinding
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.FeaturePhonesComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.PhonesDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.adapters.BestSellerAdapter
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.adapters.HotSalesAdapter
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo
import ru.effectivemobile.ecommerceconcept.navigation.getNavigationData
import ru.effectivemobile.ecommerceconcept.navigation.navigateWithInfo
import java.net.ConnectException
import java.net.UnknownHostException
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
        FeaturePhonesComponentHolder.featureComponent.inject(this@PhonesFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filterData = getNavigationData<PhoneFilterData>()

        Log.d("MyLogs", "Filter data $filterData")

        viewModel.startLoading(filterData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectData()
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.phonesData.collectLatest {
                when (it) {
                    is Response.Failure -> {
                        binding.tvBestSellerEmpty.visibility = View.VISIBLE
                        binding.tvHotSalesEmpty.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.INVISIBLE

                        if (it.exception is ConnectException || it.exception is UnknownHostException) {
                            Toast.makeText(requireContext(), "Check your network connection", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(), "Something went wrong...", Toast.LENGTH_SHORT).show()
                        }
                    }
                    is Response.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        if (it.answer.homePageProducts.isNotEmpty()) {
                            hotSalesAdapter.submitList(it.answer.homePageProducts)
                        } else {
                            binding.tvHotSalesEmpty.visibility = View.VISIBLE
                        }

                        if (it.answer.bestSellerProducts.isNotEmpty()) {
                            bestSellerAdapter.submitList(it.answer.bestSellerProducts)
                        } else {
                            binding.tvBestSellerEmpty.visibility = View.VISIBLE
                        }
                    }
                    is Response.Idle -> { }
                }
            }
        }
    }

    private fun initView() {
        binding.run {
            vpHotSales.setPageTransformer(Transformer())
            vpHotSales.adapter = hotSalesAdapter

            rvBestSeller.adapter = bestSellerAdapter
        }
    }
}