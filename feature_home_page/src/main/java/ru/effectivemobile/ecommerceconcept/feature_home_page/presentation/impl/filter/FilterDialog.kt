package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.filter

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.effectivemobile.ecommerceconcept.feature_home_page.R
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.FragmentFilterBinding
import java.text.NumberFormat
import java.util.*

internal class FilterDialog : BottomSheetDialogFragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private var filterInteract: FilterInteract? = null
    private val brands by lazy {
        resources.getStringArray(R.array.phone_brands) + getString(R.string.brand_spinner_text)
    }
    private val sizes by lazy {
        resources.getStringArray(R.array.sizes) + getString(R.string.price_spinner_text)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window?.setDimAmount(0.4f)

            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvCloseDialog.setOnClickListener {
            dismiss()
        }

        val brandAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            brands
        )

        val sizeAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            sizes
        )

        binding.run {
            tvBrandSpinner.setAdapter(brandAdapter)
            tvSizeSpinner.setAdapter(sizeAdapter)

            tvPriceSpinner.setOnClickListener {
                if (priceSlider.visibility == View.VISIBLE) {
                    root.transitionToStart()
                } else {
                    root.transitionToEnd()
                }
            }

            priceSlider.addOnChangeListener { slider, _, _ ->
                val prices = slider.values
                tvPriceSpinner.setText(getString(R.string.price_range, prices[0].toInt(), prices[1].toInt()))
            }

            priceSlider.setLabelFormatter { value ->
                val format = NumberFormat.getCurrencyInstance()
                format.maximumFractionDigits = 0
                format.currency = Currency.getInstance("USD")
                format.format(value.toDouble())
            }
        }

        filterInteract = parentFragment as FilterInteract

        binding.cvDone.setOnClickListener {
            binding.run {
                val brand = if (tvBrandSpinner.text.toString() != getString(R.string.brand_spinner_text)) {
                    tvBrandSpinner.text.toString()
                } else {
                    ""
                }

                val price = if (tvPriceSpinner.text.toString() != getString(R.string.price_spinner_text)) {
                    priceSlider.values
                } else {
                    listOf(0.0f, 10_000.0f)
                }

                filterInteract?.filter(
                    FilterDataImpl(
                        brand = brand,
                        priceTop = price[1].toInt(),
                        priceBottom = price[0].toInt(),
                        sizeTop = 5.5f,
                        sizeBottom = 4.5f
                    )
                )
            }

            dismiss()
        }
    }
}