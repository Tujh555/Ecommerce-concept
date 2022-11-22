package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.categories

import androidx.annotation.DrawableRes

internal data class Category(
    val title: String,
    @DrawableRes val selectedDrawableId: Int,
    @DrawableRes val unselectedDrawableId: Int,
)