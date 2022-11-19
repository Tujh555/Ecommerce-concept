package ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities

internal data class HomeStoreProduct(
    val id: Int,
    val isNew: Boolean,
    val title: String,
    val subtitle: String,
    val pictureUrl: String,
    val isBuy: Boolean
)