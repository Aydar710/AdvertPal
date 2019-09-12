package com.example.advertpal.features.groups

import android.support.v7.util.DiffUtil
import com.example.advertpal.data.models.groups.Group

class GroupDiffCallback : DiffUtil.ItemCallback<Group>() {

    override fun areItemsTheSame(oldGroup: Group, newGroup: Group): Boolean =
        oldGroup.id == newGroup.id


    override fun areContentsTheSame(oldGroup: Group, newGroup: Group): Boolean =
        oldGroup == newGroup
}