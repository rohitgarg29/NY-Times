package com.rg.nytimes.di.component

import android.app.Application
import com.rg.nytimes.NYTimesApplication
import com.rg.nytimes.di.module.ActivityBuilderModule
import com.rg.nytimes.di.module.ApplicationModule
import com.rg.nytimes.di.module.RetrofitModule
import com.rg.nytimes.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class,
        ApplicationModule::class, RetrofitModule::class, ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<NYTimesApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        fun build(): AppComponent
    }

}