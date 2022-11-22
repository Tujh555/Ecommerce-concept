package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.data

import android.graphics.Color
import ru.effectivemobile.core_network_impl.entities.DetailedProductModel
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.domain.PhoneDetails

internal fun DetailedProductModel.toDomain() = PhoneDetails(
    cpu = cpu,
    camera = camera,
    capacity = capacity,
    color = colors.map(Color::parseColor),
    id = id,
    images = imagesUrl,
    isFavorites = isFavorites,
    price = price,
    rating = rating,
    sd = sd,
    ssd = ssd,
    title = title
)