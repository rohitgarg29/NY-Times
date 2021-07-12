package com.rg.nytimes.activity.details

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rg.nytimes.R
import com.rg.nytimes.constants.NYConstants
import com.rg.nytimes.databinding.ActivityArticleDetailsBinding
import com.rg.nytimes.factory.ViewModelFactory
import com.rg.nytimes.model.ArticleInfo
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ArticleDetailsActivity : DaggerAppCompatActivity() {

    lateinit var articleDetailsViewModel : ArticleDetailsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var activityArticleDetailsBinding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArticleDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_details)
        val obj = intent.getParcelableExtra<ArticleInfo>(NYConstants.BUNDLE_FRAGMENT_DATA)
        articleDetailsViewModel = ViewModelProvider(this,viewModelFactory).get(ArticleDetailsViewModel::class.java)
        articleDetailsViewModel.updateImageData(obj!!.media.firstOrNull()?.mediaMetadata!!)
        activityArticleDetailsBinding.viewModel = articleDetailsViewModel
        activityArticleDetailsBinding.item = obj
        activityArticleDetailsBinding.executePendingBindings()




    }

}