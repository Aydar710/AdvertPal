package com.example.advertpal.data.models.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.timekeeper.data.network.model.groupWallRemote.Photo

data class Attachment (

    @SerializedName("type")
    @Expose
    //TODO
    val type: String? = null,
    @SerializedName("photo")
    @Expose
    val photo: Photo? = null

)
