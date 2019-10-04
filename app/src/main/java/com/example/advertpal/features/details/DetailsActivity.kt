package com.example.advertpal.features.details

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.advertpal.R
import com.example.advertpal.data.models.works.Work
import com.example.advertpal.utils.WORK_EXTRA

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val work : Work = intent.getSerializableExtra(WORK_EXTRA) as Work
        DetailsViewHolder(this).bind(work)
    }
}
