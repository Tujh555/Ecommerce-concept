package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di

import androidx.annotation.RestrictTo
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import kotlin.properties.Delegates

object CartDependencyProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    var dependencies: CartFeatureDependencies by Delegates.notNull()
}