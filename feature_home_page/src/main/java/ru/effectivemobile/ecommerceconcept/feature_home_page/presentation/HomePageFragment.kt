package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import ru.effectivemobile.ecommerceconcept.feature_home_page.R
import ru.effectivemobile.ecommerceconcept.feature_home_page.databinding.FragmentHomePageBinding
import ru.effectivemobile.ecommerceconcept.navigation.navigate

internal class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private var binding: FragmentHomePageBinding? = null
    private val strings by lazy {
        resources.getStringArray(R.array.home_page_categories)
    }
    private val categoryClickListener = CategoriesAdapter.OnCategoryClickListener {
        if (it == strings[0]) {
            navigate(R.id.action_to_phonesFragment, R.id.home_page_container)
        } else {
            navigate(R.id.action_to_notImplementedFragment, R.id.home_page_container)
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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}