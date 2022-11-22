package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain

internal class PhoneDetails(
    val cpu: String,
    val camera: String,
    val capacity: List<String>,
    val color: List<Int>,
    val id: String,
    val images: List<String>,
    var isFavorites: Boolean,
    val price: Float,
    val rating: Float,
    val sd: String,
    val ssd: String,
    val title: String
)