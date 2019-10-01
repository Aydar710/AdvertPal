package com.example.advertpal.features.works

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.advertpal.R
import com.example.advertpal.data.models.works.Work
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_work.view.*

class WorksAdapter : ListAdapter<Work, WorksAdapter.WorkHolder>(WorksDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WorkHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_work, parent, false)
        return WorkHolder(view)
    }

    override fun onBindViewHolder(holder: WorkHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class WorkHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(work: Work) {
            containerView.tv_group_name.text = work.group?.name
            containerView.tv_post_text.text = work.text

            Picasso.get()
                .load(work.group?.photo100)
                .into(containerView.iv_group)
        }
    }
}