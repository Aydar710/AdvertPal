package com.example.advertpal.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.advertpal.App
import com.example.advertpal.data.repositories.VkRepository
import javax.inject.Inject

class PostWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @Inject
    lateinit var vkRepository: VkRepository

    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun doWork(): Result {
        App.component.inject(this)
        val groupId = inputData.getString(GROUP_ID_KEY)

        val postId = if (groupId != null)
            sPref.getPostId(groupId)
        else -1

        if (postId != -1) {
            deletePost(postId)
            makePost(postId)
        } else {
            makePost(postId)
        }
        return Result.success()
    }

    @SuppressLint("CheckResult")
    private fun makePost(post_id: Int) {
        val text = inputData.getString(POST_TEXT_KEY)
        val groupId = inputData.getString(GROUP_ID_KEY)
        groupId?.let {
            vkRepository.makePost("$text : $post_id", sPref.getToken(), it)
                .subscribe({ postId ->
                    sPref.savePostId(postId, groupId)
                    Log.i("WorkState", "Post made, id : $postId")
                }, {
                    it.printStackTrace()
                    Log.i("WorkState", "Failed to make post")
                })
        }
    }

    @SuppressLint("CheckResult")
    private fun deletePost(postId: Int) {
        val groupId = inputData.getString(GROUP_ID_KEY)
        groupId?.let {groupId ->
            vkRepository.deletePost(postId, sPref.getToken(), groupId)
                .subscribe({
                    Log.i("WorkState", "Post deleted, id : $it")
                }, {
                    it.printStackTrace()
                    Log.i("WorkState", "Failed to delete post")
                })
        }
    }

}