package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di

import dagger.Component
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesApi
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules.RepositoryModule
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules.UseCasesModule
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.modules.ViewModelModule
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation.PhonesFragment

@PhonesScope
@Component(
    dependencies = [FeaturePhonesDependencies::class],
    modules = [
        RepositoryModule::class,
        UseCasesModule::class,
        ViewModelModule::class
    ]
)
internal abstract class PhonesComponent : FeaturePhonesApi {
    abstract fun inject(fragment: PhonesFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: FeaturePhonesDependencies): Builder

        fun build(): PhonesComponent
    }

    companion object {
        fun get(dependencies: FeaturePhonesDependencies) = DaggerPhonesComponent
            .builder()
            .dependencies(dependencies)
            .build()
    }
}