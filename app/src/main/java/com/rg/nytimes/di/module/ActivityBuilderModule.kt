package com.rg.nytimes.di.module

import com.rg.nytimes.activity.details.ArticleDetailsActivity
import com.rg.nytimes.activity.mainscreen.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributesInjectDetailsActivity(): ArticleDetailsActivity

}