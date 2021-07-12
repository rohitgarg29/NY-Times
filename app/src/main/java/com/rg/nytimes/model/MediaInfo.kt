package com.rg.nytimes.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaInfo(

    @SerializedName("type") val type: String,
    @SerializedName("subtype") val subtype: String,
    @SerializedName("caption") val caption: String,
    @SerializedName("copyright") val copyright: String,
    @SerializedName("approved_for_syndication") val approvedForSyndication: Int,
    @SerializedName("media-metadata") val mediaMetadata: List<MediaMetaData>
): Parcelable