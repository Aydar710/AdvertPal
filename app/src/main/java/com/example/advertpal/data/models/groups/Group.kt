package com.example.advertpal.data.models.groups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Group(

        @SerializedName("id")
        @Expose
        val id: Int? = null,
        @SerializedName("name")
        @Expose
        val name: String? = null,
        @SerializedName("screen_name")
        @Expose
        val screenName: String? = null,
        @SerializedName("is_closed")
        @Expose
        val isClosed: Int? = null,
        @SerializedName("type")
        @Expose
        val type: String? = null,
        @SerializedName("is_admin")
        @Expose
        val isAdmin: Int? = null,
        @SerializedName("is_member")
        @Expose
        val isMember: Int? = null,
        @SerializedName("is_advertiser")
        @Expose
        val isAdvertiser: Int? = null,
        @SerializedName("photo_50")
        @Expose
        val photo50: String? = null,
        @SerializedName("photo_100")
        @Expose
        val photo100: String? = null,
        @SerializedName("photo_200")
        @Expose
        val photo200: String? = null,

        var isFavorite: Boolean = false
)
