package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.effectivemobile.ecommerceconcept.feature_home_page.R
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.FragmentHomePageBinding
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di.HomePageComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.api.Filter
import ru.effectivemobile.ecommerceconcept.navigation.navigate
import ru.effectivemobile.ecommerceconcept.navigation.navigateWithInfo

internal class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private var binding: FragmentHomePageBinding? = null
    private val strings by lazy {
        resources.getStringArray(R.array.home_page_categories)
    }
    private val categoryClickListener = CategoriesAdapter.OnCategoryClickListener {
        if (it == strings[0]) {
            HomePageComponentHolder.navigationInfo?.toNavigationInfo()?.let { info ->
                navigateWithInfo(info)
            }
        } else {
            navigate(R.id.action_to_notImplementedFragment, ru.effectivemobile.ecommerceconcept.navigation.R.id.host_main)
        }
    }

    private fun initList(): List<Category> {
        val selectedDrawable = resources.obtainTypedArray(R.array.categories_pictures_selected)
        val unselectedDrawable = resources.obtainTypedArray(R.array.categories_pictures_unselected)

        val res = strings.indices.map { index ->
            val category = Category(
                title = strings[index],
                selectedDrawableId = selectedDrawable.getResourceId(index, 0),
                unselectedDrawableId = unselectedDrawable.getResourceId(index, 0)
            )

            category
        }

        selectedDrawable.recycle()
        unselectedDrawable.recycle()

        return res
    }

    private val adapter = CategoriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onCategoryClickListener = categoryClickListener

        binding?.run {
            rvCategories.adapter = adapter
            adapter.submitList(initList())

            ivFilter.setOnClickListener {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}