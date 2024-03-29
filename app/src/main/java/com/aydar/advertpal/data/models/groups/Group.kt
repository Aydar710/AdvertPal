package com.aydar.advertpal.data.models.groups

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Group(

    @SerializedName("id")
    @Expose
    val id: Long? = null,
    @SerializedName("name")
    @Expose
    val name: String? = null,
    @SerializedName("photo_50")
    @Expose
    val photo50: String? = null,
    @SerializedName("photo_100")
    @Expose
    val photo100: String? = null,
    @SerializedName("photo_200")
    @Expose
    val photo200: String? = null
) : Serializable
