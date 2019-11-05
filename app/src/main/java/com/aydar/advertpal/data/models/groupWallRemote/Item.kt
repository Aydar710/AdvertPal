package ru.timekeeper.data.network.model.groupWallRemote

import com.aydar.advertpal.data.models.groupWallRemote.Attachment
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(

    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("from_id")
    @Expose
    val fromId: Int? = null,
    @SerializedName("owner_id")
    @Expose
    val ownerId: Int? = null,
    @SerializedName("date")
    @Expose
    val date: Int? = null,
    @SerializedName("marked_as_ads")
    @Expose
    val markedAsAds: Int? = null,
    @SerializedName("post_type")
    @Expose
    val postType: String? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null,
    @SerializedName("is_pinned")
    @Expose
    val isPinned: Int? = null,
    @SerializedName("attachments")
    @Expose
    val attachments: List<Attachment>? = null,
    @SerializedName("post_source")
    @Expose
    val postSource: PostSource? = null,
    @SerializedName("comments")
    @Expose
    val comments: Comments? = null,
    @SerializedName("likes")
    @Expose
    val likes: Likes? = null,
    @SerializedName("reposts")
    @Expose
    val reposts: Reposts? = null,
    @SerializedName("views")
    @Expose
    val views: Views? = null,
    @SerializedName("is_favorite")
    @Expose
    val isFavorite: Boolean? = null,
    var groupName : String? = null,
    var groupPhoto : String? = null,
    var groupId : String? = null
)
