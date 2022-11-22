package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di

import androidx.annotation.RestrictTo
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailDependencies
import kotlin.properties.Delegates

object FeaturePhoneDetailsDependenciesProvider {
    @get:RestrictTo(RestrictTo.Scope.LIBRARY)
    var dependencies: PhoneDetailDependencies by Delegates.notNull()
}