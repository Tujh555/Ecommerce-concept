package ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.effectivemobile.ecommerceconcept.feature_cart.R
import ru.effectivemobile.ecommerceconcept.feature_cart.databinding.FragmentCartBinding
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.TAG
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.data.Response
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder
import ru.effectivemobile.ecommerceconcept.navigation.navigate
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyLogs", "Fragmetn on attach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MyLogs", "Fragmetn on create")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyLogs", "Fragmetn on create view")

        CartFeatureComponentHolder.init(CartDependencyProvider.dependencies)
        CartFeatureComponentHolder.featureComponent.inject(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MyLogs", "Fragmetn on view created")

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

        binding.rvCartProducts.adapter = adapter

        binding.goBackButton.setOnClickListener {
            navigate(R.id.action_splash)
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

                        binding.run {
                            tvTotal.text = getString(R.string.price_text, it.data.total)
                            tvDelivery.text = it.data.delivery
                        }
                    }
                }
            }
        }
    }
}