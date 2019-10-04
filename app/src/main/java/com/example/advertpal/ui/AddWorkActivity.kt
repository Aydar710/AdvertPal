package com.example.advertpal.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.work.*
import com.example.advertpal.App
import com.example.advertpal.R
import com.example.advertpal.data.models.groups.Group
import com.example.advertpal.features.works.WorksViewModel
import com.example.advertpal.utils.*
import kotlinx.android.synthetic.main.activity_add_work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddWorkActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sPref: SharedPrefWrapper

    private lateinit var viewModel: WorksViewModel

    private lateinit var viewHolder: AddWorkViewHolder

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work)
        App.component.inject(this)
        viewHolder = AddWorkViewHolder(this)
        val group = intent.getSerializableExtra(GROUP_ID_KEY)

        btn_start_job.setOnClickListener {
            startWork(group as Group)
            finish()
            Toast.makeText(this, "Периодическая публикация запущена", Toast.LENGTH_SHORT).show()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory)[WorksViewModel::class.java]
    }

    private fun startWork(group: Group) {
        val work = viewHolder.getWork()
        work.group = group

        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putString(POST_TEXT_KEY, work.text)
            .putString(GROUP_ID_KEY, work.group?.id.toString())
            .putString(WORK_ID_KEY, work.id.toString())
            .build()

        val periodicity = if (work.periodicity.isNotEmpty()) work.periodicity.toInt() else 15
        val periodicPostWorkRequest = PeriodicWorkRequest
            .Builder(PostWorker::class.java, periodicity.toLong(), TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .addTag(work.id.toString())
            .build()

        WorkManager.getInstance().enqueue(periodicPostWorkRequest)
        viewModel.addWork(work, sPref.getUserId())

        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicPostWorkRequest.id)
            .observe(this, Observer {
                Log.i("WorkState", "${it?.tags?.toString()} is  ${it?.state.toString()}")
            })
    }
}
