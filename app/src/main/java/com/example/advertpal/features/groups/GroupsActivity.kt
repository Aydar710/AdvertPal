package com.example.advertpal.features.groups

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.App
import com.example.advertpal.R
import com.example.advertpal.ui.AddWorkActivity
import kotlinx.android.synthetic.main.activity_works.*
import javax.inject.Inject

class GroupsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: GroupsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_works)
        App.component.inject(this)

        fb_add.setOnClickListener {
            startActivity(Intent(this, AddWorkActivity::class.java))
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory)[GroupsViewModel::class.java]

        print("")

    }
}
