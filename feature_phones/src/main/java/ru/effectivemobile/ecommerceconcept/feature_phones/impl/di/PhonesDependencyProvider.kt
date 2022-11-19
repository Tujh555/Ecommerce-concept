package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di

import androidx.annotation.RestrictTo
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies
import kotlin.properties.Delegates

object PhonesDependencyProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    var dependencies: FeaturePhonesDependencies by Delegates.notNull()
}