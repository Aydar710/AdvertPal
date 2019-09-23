package com.example.advertpal.features.works

import com.example.advertpal.base.BaseViewModel
import com.example.advertpal.data.models.groups.Group
import com.example.advertpal.data.models.works.Work
import com.example.advertpal.data.repositories.FireStoreRepository
import com.example.advertpal.utils.SharedPrefWrapper
import javax.inject.Inject

class WorksViewModel
@Inject constructor(
    private val sPref: SharedPrefWrapper,
    private val repository: FireStoreRepository
) : BaseViewModel() {


    fun addWork(work : Work, userId : String, group : Group){
        repository.addWork(work, userId, group)
    }
}