package com.example.advertpal.ui

import com.example.advertpal.data.models.works.Work
import kotlinx.android.synthetic.main.activity_add_work.*
import java.util.*

class AddWorkViewHolder(private val activity: AddWorkActivity) {

    fun getWork(): Work =
        Work(
            Date().time,
            activity.et_post_text.text.toString(),
            activity.et_post_periodic.text.toString(),
            null
        )
}
