package com.example.advertpal.data.models.groups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupsResponseWrapper (

    @SerializedName("response")
    @Expose
    val response: GroupsResponse? = null
)
