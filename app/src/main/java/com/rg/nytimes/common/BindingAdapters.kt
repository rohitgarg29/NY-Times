package com.rg.nytimes.common

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import com.rg.nytimes.model.ArticleInfo

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("error")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun bindImage(imgView: ImageView, imgUrl: String?) {
        loadImage(imgView, imgUrl)
    }

    @JvmStatic
    @BindingAdapter("loadArticleImage")
    fun bindImage(imgView: ImageView, article: ArticleInfo?) {
        article?.let {
            it.media.firstOrNull()?.let { media ->
                media.mediaMetadata.firstOrNull()?.let { mediaMetadata ->
                    loadImage(imgView, mediaMetadata.url, RequestOptions().circleCrop())
                }
            }
        }
    }

    private fun loadImage(imgView: ImageView, imgUrl: String?, requestOptions: RequestOptions? = null) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    (requestOptions ?: RequestOptions())
                        .placeholder(android.R.drawable.progress_horizontal)
                        .error(android.R.drawable.stat_notify_error)
                )
                .into(imgView)
        }
    }
}