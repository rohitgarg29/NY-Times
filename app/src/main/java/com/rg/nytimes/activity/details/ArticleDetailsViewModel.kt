package com.rg.nytimes.activity.details


import androidx.lifecycle.ViewModel
import com.rg.nytimes.model.MediaMetaData
import javax.inject.Inject

class ArticleDetailsViewModel
@Inject constructor(): ViewModel() {

    var imagesAdapter : ArticleImageAdapter


    init {
        imagesAdapter = ArticleImageAdapter(this)
    }

    fun updateImageData(listItems: List<MediaMetaData>) {
        this.imagesAdapter.updateItemList(listItems.reversed())
    }

    fun getImageAdapter(): ArticleImageAdapter {
        return imagesAdapter
    }

}
