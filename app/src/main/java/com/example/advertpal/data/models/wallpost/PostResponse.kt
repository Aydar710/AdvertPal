package com.example.advertpal.data.models.wallpost

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PostResponse(

    @SerializedName("post_id")
    @Expose
    var postId: Int? = null
)