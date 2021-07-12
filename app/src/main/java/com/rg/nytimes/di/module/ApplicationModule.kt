package com.rg.nytimes.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule{

    @Provides
    @Singleton
    fun provideContext(app : Application) : Context {
        return app.applicationContext
    }

}