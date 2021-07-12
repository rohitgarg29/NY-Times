package com.rg.nytimes

import com.rg.nytimes.constants.NYConstants
import com.rg.nytimes.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class NYTimesApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).baseUrl(NYConstants.BASE_URL).build()

    }
}