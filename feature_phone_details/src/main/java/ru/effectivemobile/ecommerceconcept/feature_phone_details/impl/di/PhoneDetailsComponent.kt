package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di

import dagger.Component
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailDependencies
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailsApi
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.modules.UseCasesModule
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.modules.ViewModelModule
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.presentation.PhoneDetailsFragment

@Component(
    dependencies = [PhoneDetailDependencies::class],
    modules = [
        UseCasesModule::class,
        ViewModelModule::class
    ]
)
internal interface PhoneDetailsComponent : PhoneDetailsApi {
    fun inject(fragment: PhoneDetailsFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: PhoneDetailDependencies): Builder

        fun build(): PhoneDetailsComponent
    }

    companion object {
        fun get(dependencies: PhoneDetailDependencies) = DaggerPhoneDetailsComponent
            .builder()
            .dependencies(dependencies)
            .build()
    }
}