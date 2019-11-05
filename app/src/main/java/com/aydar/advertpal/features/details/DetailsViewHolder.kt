package com.aydar.advertpal.features.details

import android.support.v7.app.AppCompatActivity
import com.aydar.advertpal.data.models.works.Work
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsViewHolder(private val activity: AppCompatActivity) {

    fun bind(work: Work) {
        with(activity){
            tv_group_name.text = work.group?.name
            tv_post_text.text = work.text

            Picasso.get()
                .load(work.group?.photo200)
                .into(iv_group_photo)
        }
    }
}