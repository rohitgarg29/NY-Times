package com.rg.nytimes.service

import com.rg.nytimes.constants.NYConstants
import com.rg.nytimes.model.ArticlesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitClient {

    @GET(NYConstants.POPULAR_ARTICLES_LIST)
    fun mostPopularArticles(@Path("period") period: String, @Query("api-key") apiKey: String): Single<ArticlesResponse>
}