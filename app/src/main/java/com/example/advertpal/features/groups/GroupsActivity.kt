package com.example.advertpal.features.groups

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.App
import com.example.advertpal.R
import com.example.advertpal.data.models.groups.Group
import com.example.advertpal.ui.AddWorkActivity
import com.example.advertpal.utils.GROUP_KEY
import com.example.advertpal.utils.SharedPrefWrapper
import kotlinx.android.synthetic.main.activity_groups.*
import javax.inject.Inject

class GroupsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GroupsViewModel

    private lateinit var adapter: GroupsAdapter

    @Inject
    lateinit var sPref : SharedPrefWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_groups)
        App.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[GroupsViewModel::class.java]

        adapter = GroupsAdapter {
            startAddingActivity(it)
        }
        rv_groups.adapter = adapter
        initObservers()
        viewModel.showGroups(sPref.getUserId())
    }

    private fun initObservers() {
        viewModel.groupsLiveData.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    private fun startAddingActivity(group: Group) {
        val intent = Intent(this, AddWorkActivity::class.java).apply {
            putExtra(GROUP_KEY, group)
        }
        startActivity(intent)
    }
}
