package com.aydar.advertpal.data.models.wallpost

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DeletedPostResponse(

    @SerializedName("response")
    @Expose
    var response: Int? = null

)