package com.aydar.advertpal.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aydar.advertpal.App
import com.aydar.advertpal.data.repositories.VkRepository
import javax.inject.Inject

class PostWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    @Inject
    lateinit var vkRepository: VkRepository

    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun doWork(): Result {
        App.component.inject(this)
        val groupId = inputData.getString(GROUP_ID_KEY)
        val workId = inputData.getString(WORK_ID_KEY)

        val postId = if (workId != null)
            sPref.getPostId(workId)
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
        val workId = inputData.getString(WORK_ID_KEY)
        workId?.let {
            groupId?.let { grId ->
                vkRepository.makePost("$text", sPref.getToken(), grId)
                    .subscribe({ postId ->
                        sPref.savePostId(postId, workId)
                        Log.i("WorkState", "Post made, id : $postId")
                    }, {
                        it.printStackTrace()
                        Log.i("WorkState", "Failed to make post")
                    })
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun deletePost(postId: Int) {
        val groupId = inputData.getString(GROUP_ID_KEY)

        vkRepository.deletePost(postId, sPref.getToken(), groupId!!)
            .subscribe({
                Log.i("WorkState", "Post deleted, id : $it")
            }, {
                it.printStackTrace()
                Log.i("WorkState", "Failed to delete post")
            })

    }

}