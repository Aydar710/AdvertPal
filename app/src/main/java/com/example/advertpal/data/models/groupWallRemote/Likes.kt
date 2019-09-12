package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Likes (

    @SerializedName("count")
    @Expose
    var count: Int? = null,
    @SerializedName("user_likes")
    @Expose
    var userLikes: Int? = null,
    @SerializedName("can_like")
    @Expose
    val canLike: Int? = null,
    @SerializedName("can_publish")
    @Expose
    val canPublish: Int? = null

)
