package com.example.advertpal.repositories

import com.example.advertpal.services.VkService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.timekeeper.data.network.model.groupWallRemote.Group
import ru.timekeeper.data.network.model.groupWallRemote.Item

class VkRepository(private val vkService: VkService) {

    fun makePost(message: String, token: String): Single<Int> =
        vkService.makePost(message = message, access_token = token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.response?.postId
            }

    fun deletePost(postId: Int, token: String): Single<Int> =
        vkService.deletePost(postId = postId.toString(), access_token = token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.response
            }

    fun getGroupPosts(
        count: String = "10", token: String,
        currentPage: Int = 0, pagSize: Int = 10
    ): Single<List<Item>> {
        return vkService.getGroupPosts(count = count, access_token = token, offset = "${currentPage * pagSize}")
            .subscribeOn(Schedulers.io())
            .map {
                it.response?.items
            }
    }
}
