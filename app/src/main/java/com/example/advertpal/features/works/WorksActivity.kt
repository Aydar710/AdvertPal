package com.example.advertpal.features.works

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.R
import com.example.advertpal.features.groups.GroupsActivity
import kotlinx.android.synthetic.main.activity_works.*

class WorksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_works)

        fb_add.setOnClickListener {
            startActivity(Intent(this, GroupsActivity::class.java))
        }
    }
}
