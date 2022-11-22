package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.filter

import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhoneFilterData

interface FilterInteract {
    fun filter(filterData: PhoneFilterData)
}