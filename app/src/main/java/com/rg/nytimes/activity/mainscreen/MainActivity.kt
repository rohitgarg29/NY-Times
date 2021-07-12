package com.rg.nytimes.activity.mainscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rg.nytimes.R
import com.rg.nytimes.activity.details.ArticleDetailsActivity
import com.rg.nytimes.constants.NYConstants
import com.rg.nytimes.databinding.ActivityMainBinding
import com.rg.nytimes.factory.ViewModelFactory
import com.rg.nytimes.model.ArticleInfo
import com.rg.nytimes.model.ArticlesResponse
import com.rg.nytimes.service.Resource
import com.rg.nytimes.service.ResponseStatus
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    lateinit var mainActivityViewModel : ArticleListViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this,viewModelFactory).get(ArticleListViewModel::class.java)
        activityMainBinding.viewModel = mainActivityViewModel


        setupObservers()
    }

    fun setupObservers() {

        mainActivityViewModel.dataResponse.observe(this, Observer<Resource<Any>> { response ->
            when (response!!.status) {
                ResponseStatus.SUCCESS -> {
                    activityMainBinding.cvProgressBar.visibility = View.GONE
                    val articlesResponse = response.data as ArticlesResponse
                    var articlesList = articlesResponse.results
                    if (null != articlesList && articlesList!!.size > 0) {
                        mainActivityViewModel.updateArticlesListAdapter(articlesList!!)
                    } else {
                        activityMainBinding.rvArticlesList.visibility = View.GONE
                        activityMainBinding.tvNoArticles.visibility = View.VISIBLE
                    }
                }
                ResponseStatus.LOADING -> {
                    //customProgressDialog.show()
                }
                ResponseStatus.ERROR -> {
                    activityMainBinding.cvProgressBar.visibility = View.GONE
                    Toast.makeText(this@MainActivity, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        mainActivityViewModel.getAction().observe(this, Observer { obj ->
            when (obj) {
                is ArticleInfo -> {
                    val intent = Intent(this, ArticleDetailsActivity::class.java)
                    intent.putExtra(NYConstants.BUNDLE_FRAGMENT_DATA, obj)
                    startActivity(intent)
                }
            }

        })
    }
}
