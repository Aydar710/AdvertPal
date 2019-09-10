package ru.timekeeper.data.network.model.groupWallRemote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reposts (

    @SerializedName("count")
    @Expose
    var count: Int? = null,
    @SerializedName("user_reposted")
    @Expose
    var userReposted: Int? = null

)
