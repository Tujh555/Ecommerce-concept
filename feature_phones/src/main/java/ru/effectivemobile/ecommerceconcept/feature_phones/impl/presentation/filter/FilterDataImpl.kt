package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.filter

import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.PhoneFilterData

internal class FilterDataImpl(
    override val brand: String,
    override val priceTop: Int,
    override val priceBottom: Int,
    override val sizeTop: Float,
    override val sizeBottom: Float
) : PhoneFilterData