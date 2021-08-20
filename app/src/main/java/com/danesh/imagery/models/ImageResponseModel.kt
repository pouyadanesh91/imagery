package com.danesh.imagery.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class ImageResponseModel: Serializable {

    @SerializedName("total")
    var total: Int= 0

    @SerializedName("totalHits")
    var totalHits: Int= 0

    @SerializedName("hits")
    var hits: ArrayList<ImageHitModel>? = null
}