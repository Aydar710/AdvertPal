package com.aydar.advertpal.data.repositories

import com.aydar.advertpal.data.models.groups.Group
import com.aydar.advertpal.data.services.VkService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VkRepository(private val vkService: VkService) {

    fun makePost(message: String, token: String, groupId: String): Single<Int> =
        vkService.makePost(message = message, access_token = token, ownerId = "-$groupId")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.response?.postId
            }

    fun deletePost(postId: Int, token: String, groupId: String): Single<Int> =
        vkService.deletePost(
            postId = postId.toString(),
            access_token = token,
            ownerId = "-$groupId"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.response
            }

    fun getUserGroups(
        userId: String,
        token: String,
        count: String = "300",
        currentPage: Int = 0,
        pageSize: Int = 0
    ): Single<List<Group>> =
        vkService.getUsersGroups(userId, count, token = token, offset = "${currentPage * pageSize}")
            .subscribeOn(Schedulers.io())
            .map {
                it.response?.items
            }
}
