package com.example.advertpal.features.works

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.App
import com.example.advertpal.R
import com.example.advertpal.features.groups.GroupsActivity
import kotlinx.android.synthetic.main.activity_works.*
import javax.inject.Inject

class WorksActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: WorksViewModel

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_works)
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[WorksViewModel::class.java]
        viewModel.getWorks("116812347")
            .subscribe({
                print(it)
            },{
                it.printStackTrace()
            })

        fb_add.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }
    }
}
