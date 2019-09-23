package com.example.advertpal.ui

import com.example.advertpal.data.models.works.Work
import kotlinx.android.synthetic.main.activity_add_work.*

class AddWorkViewHolder(private val activity: AddWorkActivity) {

    fun getWork(groupId: String): Work =
        Work(
            groupId,
            activity.et_post_text.text.toString(),
            activity.et_post_periodic.text.toString()
        )
}
