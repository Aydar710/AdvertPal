package com.aydar.advertpal.data.services

import com.aydar.advertpal.data.models.groups.GroupsResponseWrapper
import com.aydar.advertpal.data.models.wallpost.DeletedPostResponse
import com.aydar.advertpal.data.models.wallpost.PostResponseWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VkService {

    @POST("wall.post")
    fun makePost(
        @Query("owner_id") ownerId: String = "-183072058",
        @Query("message") message: String,
        @Query("access_token") access_token: String
    ): Single<PostResponseWrapper>

    @GET("wall.delete")
    fun deletePost(
        @Query("owner_id") ownerId: String = "-183072058",
        @Query("post_id") postId: String,
        @Query("access_token") access_token: String
    ): Single<DeletedPostResponse>

    @GET("groups.get")
    fun getUsersGroups(
        @Query("user_id") userId: String,
        @Query("extended") extended: String = "1",
        @Query("count") count: String = "300",
        @Query("access_token") token: String,
        @Query("offset") offset: String = ""
    ): Single<GroupsResponseWrapper>
}
