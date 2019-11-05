package com.aydar.advertpal.features.works

import android.support.v7.util.DiffUtil
import com.aydar.advertpal.data.models.works.Work

class WorksDiffCallback : DiffUtil.ItemCallback<Work>() {

    override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean =
        oldItem == newItem
}
