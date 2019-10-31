package com.example.advertpal.features.groups

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.advertpal.R
import com.example.advertpal.data.models.groups.Group
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_group.*
import kotlinx.android.synthetic.main.item_group.view.*

class GroupsAdapter(
    private val listitemClickListener: (Group) -> Unit
) : ListAdapter<Group, GroupsAdapter.GroupHolder>(GroupDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return GroupHolder(view)
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
    }

    inner class GroupHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        var imgGroup = containerView.img_group

        fun bind(group: Group) {
            txt_group_name.text = group.name

            containerView.setOnClickListener {
                group.let { group -> listitemClickListener.invoke(group) }
            }

            Picasso.get()
                .load(group.photo100)
                .placeholder(R.drawable.placeholder)
                .into(imgGroup)

        }
    }
}