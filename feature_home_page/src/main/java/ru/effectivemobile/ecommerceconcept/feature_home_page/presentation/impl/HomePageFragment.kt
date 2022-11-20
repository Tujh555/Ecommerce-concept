package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.effectivemobile.ecommerceconcept.feature_home_page.R
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.FragmentHomePageBinding
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di.HomePageComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhoneFilterData
import ru.effectivemobile.ecommerceconcept.navigation.navigate
import ru.effectivemobile.ecommerceconcept.navigation.navigateWithInfo

internal class HomePageFragment : Fragment(R.layout.fragment_home_page), FilterInteract {
    private val binding by viewBinding(FragmentHomePageBinding::bind)
    private val strings by lazy {
        resources.getStringArray(R.array.home_page_categories)
    }
    private val categoryClickListener = CategoriesAdapter.OnCategoryClickListener {
        if (it == strings[0]) {
            HomePageComponentHolder.phonesNavigationInfo?.toNavigationInfo()?.let { info ->
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.onCategoryClickListener = categoryClickListener

        binding.run {
            rvCategories.adapter = adapter
            adapter.submitList(initList())

            ivFilter.setOnClickListener {
                FilterDialog().show(childFragmentManager, "")
            }

            bottomNavigation?.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.home -> {
                        HomePageComponentHolder.phonesNavigationInfo?.let { info ->
                            navigateWithInfo(info.toNavigationInfo())
                        }
                    }

                    R.id.cart -> {
                        HomePageComponentHolder.cartNavigationInfo?.let { info ->
                            navigateWithInfo(info.toNavigationInfo())
                        }
                    }
                }

                true
            }
        }
    }

    override fun filter(filterData: PhoneFilterData) {
        HomePageComponentHolder.phonesNavigationInfo?.let { info ->
            navigateWithInfo(info.toNavigationInfo(), filterData)
        }
    }
}