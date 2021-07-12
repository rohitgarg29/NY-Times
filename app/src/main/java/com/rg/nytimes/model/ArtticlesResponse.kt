package com.rg.nytimes.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponse (

    @SerializedName("status") val status : String,
    @SerializedName("copyright") val copyright : String,
    @SerializedName("num_results") val numResults : Int,
    @SerializedName("results") val results : List<ArticleInfo>
)