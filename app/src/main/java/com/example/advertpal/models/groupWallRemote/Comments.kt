package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comments (

    @SerializedName("count")
    @Expose
    val count: Int? = null,
    @SerializedName("can_post")
    @Expose
    val canPost: Int? = null,
    @SerializedName("groups_can_post")
    @Expose
    val groupsCanPost: Boolean? = null

)
