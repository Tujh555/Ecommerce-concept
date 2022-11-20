package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.filter

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.effectivemobile.ecommerceconcept.feature_phones.R
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.FilterInteract
import ru.effectivemobile.ecommerceconcept.feature_phones.databinding.FragmentFilterBinding

internal class FilterDialog : BottomSheetDialogFragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)
    private var filterInteract: FilterInteract? = null

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

        filterInteract = parentFragment as? FilterInteract

        binding.cvDone.setOnClickListener {
            filterInteract?.filter(
                FilterDataImpl(
                    brand = "Xiaomi",
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