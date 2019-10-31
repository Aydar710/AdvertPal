package com.example.advertpal.features.works

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.example.advertpal.App
import com.example.advertpal.R
import com.example.advertpal.base.BaseActivity
import com.example.advertpal.base.Commands
import com.example.advertpal.data.models.works.Work
import com.example.advertpal.features.details.DetailsActivity
import com.example.advertpal.features.groups.GroupsActivity
import com.example.advertpal.utils.WORK_EXTRA
import kotlinx.android.synthetic.main.activity_works.*
import javax.inject.Inject


class WorksActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WorksViewModel

    private lateinit var adapter: WorksAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_works)
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[WorksViewModel::class.java]
        initRecycler()
        initObservers()
        initCommandObservers()
        initNetworkSnackBar(R.id.activity_works)
        checkConnection()
        showData()
        fb_add.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getWorks("116812347")
    }

    override fun showData() {
        viewModel.getWorks("116812347")
    }

    private fun initRecycler() {
        adapter = WorksAdapter({
            onDeleteClicked(it)
        }, {
            onGroupClicked(it)
        })
        rv_works.adapter = adapter
    }

    private fun initObservers() {
        viewModel.worksLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initCommandObservers() {
        viewModel.command.observe(this, Observer {
            when (it) {
                is Commands.ShowProgress -> showProgress()
                is Commands.HideProgress -> hideProgress()
            }
        })
    }

    private fun onDeleteClicked(workId: Long) {
        viewModel.deleteWork(workId)
    }

    private fun onGroupClicked(work: Work) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
            .apply {
                putExtra(WORK_EXTRA, work)
            }

        startActivity(detailsIntent)
    }

}
