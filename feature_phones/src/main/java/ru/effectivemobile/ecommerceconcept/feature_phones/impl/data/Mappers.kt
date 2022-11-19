package ru.effectivemobile.ecommerceconcept.feature_phones.impl.data

import ru.effectivemobile.core_network_impl.entities.BestSellerProductModel
import ru.effectivemobile.core_network_impl.entities.HomePageDataModel
import ru.effectivemobile.core_network_impl.entities.HomeStoreProductModel
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.BestSellerProduct
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomePageData
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities.HomeStoreProduct

internal fun BestSellerProductModel.toDomain(): BestSellerProduct = BestSellerProduct(
    id = id,
    isFavorites = isFavorites,
    title = title,
    priceWithDiscount = priceWithDiscount,
    priceWithoutDiscount = priceWithoutDiscount,
    pictureUrl = pictureUrl
)

internal fun HomeStoreProductModel.toDomain(): HomeStoreProduct = HomeStoreProduct(
    id = id,
    isNew = isNew,
    title = title,
    subtitle = subtitle,
    pictureUrl = pictureUrl,
    isBuy = isBuy
)

internal fun HomePageDataModel.toDomain(): HomePageData = HomePageData(
    bestSellerProducts = bestSeller.map { it.toDomain() },
    homePageProducts = homeStore.map { it.toDomain() }
)