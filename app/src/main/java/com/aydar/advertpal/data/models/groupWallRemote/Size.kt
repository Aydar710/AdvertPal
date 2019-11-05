package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Size(

    @SerializedName("type")
    @Expose
    val type: String? = null,
    @SerializedName("url")
    @Expose
    val url: String? = null,
    @SerializedName("width")
    @Expose
    val width: Int? = null,
    @SerializedName("height")
    @Expose
    val height: Int? = null

)
