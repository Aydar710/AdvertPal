package com.aydar.advertpal.data.models.groups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupsResponse (

    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("items")
    @Expose
    val items: List<Group>? = null
)
