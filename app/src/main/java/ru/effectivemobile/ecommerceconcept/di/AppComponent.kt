package ru.effectivemobile.ecommerceconcept.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.effectivemobile.ecommerceconcept.di.modules.ApiModule
import ru.effectivemobile.ecommerceconcept.di.scopes.AppScope

@Component(modules = [])
@AppScope
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}