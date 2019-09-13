package com.example.advertpal.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.*
import com.example.advertpal.R
import com.example.advertpal.utils.GROUP_ID_KEY
import com.example.advertpal.utils.POST_TEXT_KEY
import com.example.advertpal.utils.PostWorker
import com.example.advertpal.utils.WorkDataKeyGenerator
import kotlinx.android.synthetic.main.activity_add_work.*
import java.util.concurrent.TimeUnit

class AddWorkActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work)

        btn_start_job.setOnClickListener {
            startWork()
        }
    }

    private fun startWork(groupId: String) {
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putString(POST_TEXT_KEY, et_post_text.text.toString())
            .putString(GROUP_ID_KEY, groupId)
            .build()

        val etPeriodicity = et_post_periodic.text.toString()
        val periodicity = if (etPeriodicity.isNotEmpty()) etPeriodicity.toInt() else 15
        val periodicPostWorkRequest = PeriodicWorkRequest
            .Builder(PostWorker::class.java, periodicity.toLong(), TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .addTag("TAG")
            .build()

        WorkManager.getInstance().enqueue(periodicPostWorkRequest)

        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicPostWorkRequest.id)
            .observe(this, Observer {
                Log.i("WorkState", it?.state.toString())
            })
    }
}
