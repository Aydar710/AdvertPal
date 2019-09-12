package com.example.advertpal.features.groups

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import com.example.advertpal.base.BaseViewModel
import com.example.advertpal.data.models.groups.Group
import com.example.advertpal.data.repositories.VkRepository
import com.example.advertpal.utils.SharedPrefWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GroupsViewModel
@Inject constructor(
    private val sPref: SharedPrefWrapper,
    private val repository: VkRepository
) : BaseViewModel() {

    val groupsLiveData = MutableLiveData<List<Group>>()

    @SuppressLint("CheckResult")
    fun showGroups(userId : String) {
        repository.getUserGroups(userId, sPref.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositDisposable.add(it)
            }
            .subscribe({
                groupsLiveData.postValue(it)
            },{
                it.printStackTrace()
            })
    }
}