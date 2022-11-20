package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.effectivemobile.ecommerceconcept.feature_home_page.R
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.FragmentFilterBinding

internal class FilterDialog : BottomSheetDialogFragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private var filterInteract: FilterInteract? = null
    private val brands by lazy {
        resources.getStringArray(R.array.phone_brands)
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

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_expandable_list_item_1,
            brands
        )

        binding.tvBrandSpinner.setAdapter(adapter)

        filterInteract = parentFragment as FilterInteract

        binding.cvDone.setOnClickListener {
            filterInteract?.filter(
                FilterDataImpl(
                    brand = "Aboba",
                    priceTop = 500,
                    priceBottom = 300,
                    sizeTop = 5.5f,
                    sizeBottom = 4.5f
                )
            )

            dismiss()
        }
    }
}