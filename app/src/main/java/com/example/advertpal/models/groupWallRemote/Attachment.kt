package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Attachment (

    @SerializedName("type")
    @Expose
    //TODO
    val type: String? = null,
    @SerializedName("photo")
    @Expose
    val photo: Photo? = null

)
