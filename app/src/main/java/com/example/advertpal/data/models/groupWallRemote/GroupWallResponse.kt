package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GroupWallResponse(

    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("items")
    @Expose
    val items: List<Item>? = null,
    @SerializedName("profiles")
    @Expose
    val profiles: List<Any>? = null,
    @SerializedName("groups")
    @Expose
    val groups: List<Group>? = null

)
