package ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation

import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.SimpleItemAnimator
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.effectivemobile.ecommerceconcept.feature_cart.R
import ru.effectivemobile.ecommerceconcept.feature_cart.databinding.FragmentCartBinding
import ru.effectivemobile.core_network_impl.entities.Response
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder
import java.util.*
import javax.inject.Inject

internal class CartFragment : Fragment(R.layout.fragment_cart) {
    @Inject
    lateinit var viewModelsFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: CartItemsAdapter

    private val binding by viewBinding(FragmentCartBinding::bind)
    private val viewModel by viewModels<CartViewModel> {
        viewModelsFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        CartFeatureComponentHolder.init(CartDependencyProvider.dependencies)
        CartFeatureComponentHolder.featureComponent.inject(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeConnectionState()
        viewModel.loadUserCart()
        initUI()
        observeFlows()
    }

    private fun initUI() {
        adapter.apply {
            onItemDelete = viewModel::takeOutItem
            onItemPlus = viewModel::addItem
            onItemMinus = viewModel::deleteItem
        }

        (binding.rvCartProducts.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        binding.rvCartProducts.adapter = adapter

        binding.goBackButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observeFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.cartProducts.collect {
                adapter.submitList(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.cartData.collect {
                when (it) {
                    is Response.Failure -> {
                        binding.clSuccessLoading.visibility = View.GONE
                        binding.clFailureLayout.visibility = View.VISIBLE

                        it.exception.printStackTrace()
                    }
                    is Response.Idle -> Toast.makeText(
                        requireContext(),
                        getString(R.string.idle_state_text),
                        Toast.LENGTH_SHORT
                    ).show()
                    is Response.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Response.Success -> {
                        binding.clSuccessLoading.visibility = View.VISIBLE
                        binding.clFailureLayout.visibility = View.GONE
                        binding.progressBar.visibility = View.INVISIBLE

                        Log.d("MyLogs", "${it.data}")

                        binding.run {
                            tvTotal.text = getString(
                                R.string.total_sum_text,
                                String.format(Locale.US, "%,d", it.data.total.toInt())
                            )
                            tvDelivery.text = it.data.delivery
                        }
                    }
                }
            }
        }
    }

    private fun observeConnectionState() {
        val connectivityManager = requireContext().getSystemService(ConnectivityManager::class.java)

        connectivityManager.registerDefaultNetworkCallback(
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    viewModel.loadUserCart()
                }
            }
        )
    }
}