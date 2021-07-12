package com.rg.nytimes.di.module

import androidx.lifecycle.ViewModel
import com.rg.nytimes.activity.details.ArticleDetailsViewModel
import com.rg.nytimes.di.keys.ViewModelKey
import com.rg.nytimes.activity.mainscreen.ArticleListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticleListViewModel::class)
    abstract fun bindMainViewModel(mainActivityViewModel: ArticleListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailsViewModel::class)
    abstract fun bindDetailsViewModel(mainActivityViewModel: ArticleDetailsViewModel) : ViewModel

}