package com.aydar.advertpal.data.models.works

import com.aydar.advertpal.data.models.groups.Group
import java.io.Serializable

data class Work(
    val id: Long,
    val text: String,
    val periodicity: String,
    var group: Group?
) : Serializable