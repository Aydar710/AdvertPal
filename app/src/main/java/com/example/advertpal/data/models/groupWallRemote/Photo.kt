package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Photo(

    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("album_id")
    @Expose
    val albumId: Int? = null,
    @SerializedName("owner_id")
    @Expose
    val ownerId: Int? = null,
    @SerializedName("user_id")
    @Expose
    val userId: Int? = null,
    @SerializedName("sizes")
    @Expose
    val sizes: List<Size>? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null,
    @SerializedName("date")
    @Expose
    val date: Int? = null,
    @SerializedName("post_id")
    @Expose
    val postId: Int? = null,
    @SerializedName("access_key")
    @Expose
    val accessKey: String? = null

)
