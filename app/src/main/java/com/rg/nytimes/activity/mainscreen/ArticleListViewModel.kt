package com.rg.nytimes.activity.mainscreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rg.nytimes.constants.NYConstants
import com.rg.nytimes.model.ArticleInfo
import com.rg.nytimes.service.Resource
import com.rg.nytimes.service.RetrofitClient
import com.rg.nytimes.viewmodel.addToDisposable
import com.rg.nytimes.viewmodel.removeAllDisposables
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticleListViewModel
@Inject constructor(private val apiClient: RetrofitClient): ViewModel() {

    val TAG = ArticleListViewModel::class.java.simpleName
    val dataResponse : MutableLiveData<Resource<Any>> = MutableLiveData()
    var adapterArticleList : ArticlesListAdapter
    private val mAction: MutableLiveData<Any> = MutableLiveData<Any>()

    init {
        addToDisposable(
            apiClient.mostPopularArticles(period = "7", apiKey = NYConstants.API_KEY_NY_TIMES).subscribeOn(
                Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {response -> Log.i(TAG,response.toString())
                        dataResponse.postValue(Resource.success(response))},
                    {error -> Log.i(TAG,error.toString())
                        dataResponse.postValue(Resource.error(
                            error.toString(), null))}
                )
        )
        adapterArticleList = ArticlesListAdapter(this)

    }

    override fun onCleared() {
        super.onCleared()
        removeAllDisposables()
    }

    fun getAction(): LiveData<Any> {
        return mAction
    }

    fun updateArticlesListAdapter(listItems: List<ArticleInfo>) {
        this.adapterArticleList.updateItemList(listItems)
    }

    fun getArticlesListAdapter(): ArticlesListAdapter {
        return adapterArticleList
    }

    fun onItemClicked(obj: ArticleInfo) {
        mAction.postValue(obj)
    }
}
