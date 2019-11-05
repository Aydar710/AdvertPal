package com.aydar.advertpal.features.groups

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.aydar.advertpal.App
import com.aydar.advertpal.R
import com.aydar.advertpal.base.BaseActivity
import com.aydar.advertpal.base.Commands
import com.aydar.advertpal.data.models.groups.Group
import com.aydar.advertpal.ui.AddWorkActivity
import com.aydar.advertpal.utils.GROUP_ID_KEY
import com.aydar.advertpal.utils.SharedPrefWrapper
import kotlinx.android.synthetic.main.activity_groups.*
import javax.inject.Inject

class GroupsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GroupsViewModel

    private lateinit var adapter: GroupsAdapter

    @Inject
    lateinit var sPref: SharedPrefWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        App.component.inject(this)
        setSupportActionBar(inc_toolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[GroupsViewModel::class.java]
        progressBar = pb_groups
        initRecycler()
        initObservers()
        initCommandObservers()
        initNetworkSnackBar(R.id.activity_groups)
        checkConnection()
        showData()
    }

    override fun showData() {
        viewModel.showGroups(sPref.getUserId())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initObservers() {
        viewModel.groupsLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun initCommandObservers() {
        viewModel.command.observe(this, Observer {
            when(it){
                is Commands.ShowProgress -> showProgress()
                is Commands.HideProgress -> hideProgress()
            }
        })
    }

    private fun startAddingActivity(group: Group) {
        val intent = Intent(this, AddWorkActivity::class.java).apply {
            putExtra(GROUP_ID_KEY, group)
        }
        startActivity(intent)
    }

    private fun initRecycler() {
        adapter = GroupsAdapter {
            startAddingActivity(it)
        }
        rv_groups.adapter = adapter
    }
}
