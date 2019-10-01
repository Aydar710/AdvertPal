package com.example.advertpal.data.models.works

import com.example.advertpal.data.models.groups.Group

data class Work(
    val id: Long,
    val text: String,
    val periodicity: String,
    var group: Group?
)