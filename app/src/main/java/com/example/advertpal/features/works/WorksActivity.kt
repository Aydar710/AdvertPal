package com.example.advertpal.features.works

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import com.example.advertpal.App
import com.example.advertpal.base.BaseActivity
import com.example.advertpal.base.Commands
import com.example.advertpal.data.models.works.Work
import com.example.advertpal.features.details.DetailsActivity
import com.example.advertpal.features.groups.GroupsActivity
import com.example.advertpal.utils.USER_ID_EXTRA
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
        setContentView(com.example.advertpal.R.layout.activity_works)
        setSupportActionBar(inc_toolbar as Toolbar)
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[WorksViewModel::class.java]
        initRecycler()
        initObservers()
        initCommandObservers()
        initNetworkSnackBar(com.example.advertpal.R.id.activity_works)
        checkConnection()
        showData()
        fb_add.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }

        progressBar = pb_works

    }

    override fun onResume() {
        super.onResume()
        showData()
    }

    override fun showData() {
        viewModel.getWorks("116812347")
    }

    private fun initRecycler() {
        adapter = WorksAdapter({
            onDeleteClicked(it)
        }, { work, imageView ->
            onGroupClicked(work, "116812347", imageView)
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
                is Commands.HasNoWorks -> tv_on_empty_screen.visibility = View.VISIBLE
                is Commands.HasWorks -> tv_on_empty_screen.visibility = View.GONE
            }
        })
    }

    private fun onDeleteClicked(workId: Long) {
        viewModel.deleteWork(workId)
    }

    private fun onGroupClicked(work: Work, userId: String, image: ImageView) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
            .apply {
                putExtra(WORK_EXTRA, work)
                putExtra(USER_ID_EXTRA, userId)
            }

        val options = ViewCompat.getTransitionName(image)?.let {
            ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, image, it)
        }

        startActivity(detailsIntent, options?.toBundle())
    }
}
