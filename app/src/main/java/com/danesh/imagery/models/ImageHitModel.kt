package com.danesh.imagery.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ImageHitModel: Serializable {

    @SerializedName("id")
    var _id: Int= 0

    @SerializedName("pageURL")
    var pageURL: String= ""

    @SerializedName("type")
    var type: String= ""

    @SerializedName("previewURL")
    var previewURL: String= ""

    @SerializedName("previewWidth")
    var previewWidth: Int= 0

    @SerializedName("previewHeight")
    var previewHeight: Int= 0

    @SerializedName("webformatURL")
    var webformatURL: String= ""

    @SerializedName("webformatWidth")
    var webformatWidth: Int= 0

    @SerializedName("webformatHeight")
    var webformatHeight: Int= 0

    @SerializedName("largeImageURL")
    var largeImageURL: String= ""

    @SerializedName("fullHDURL")
    var fullHDURL: String= ""

    @SerializedName("imageURL")
    var imageURL: String= ""

    @SerializedName("imageWidth")
    var imageWidth: Int= 0

    @SerializedName("imageHeight")
    var imageHeight: Int= 0

    @SerializedName("imageSize")
    var imageSize: Int= 0

    @SerializedName("views")
    var views: Int= 0

    @SerializedName("downloads")
    var downloads: Int= 0

    @SerializedName("likes")
    var likes: Int= 0

    @SerializedName("comments")
    var comments: Int= 0

    @SerializedName("user_id")
    var user_id: Int= 0

    @SerializedName("user")
    var user: String= ""

    @SerializedName("userImageURL")
    var userImageURL: String= ""

}