package com.aydar.advertpal.features.groups

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.aydar.advertpal.base.BaseViewModel
import com.aydar.advertpal.base.Commands
import com.aydar.advertpal.base.SingleLiveEvent
import com.aydar.advertpal.data.models.groups.Group
import com.aydar.advertpal.data.repositories.VkRepository
import com.aydar.advertpal.utils.SharedPrefWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GroupsViewModel
@Inject constructor(
    private val sPref: SharedPrefWrapper,
    private val repository: VkRepository
) : BaseViewModel() {

    val groupsLiveData = MutableLiveData<List<Group>>()

    protected val _command: SingleLiveEvent<Commands> = SingleLiveEvent()

    val command: LiveData<Commands>
        get() = _command

    @SuppressLint("CheckResult")
    fun showGroups(userId: String) {
        repository.getUserGroups(userId, sPref.getToken())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositDisposable.add(it)
                _command.value = Commands.ShowProgress()
            }
            .doFinally {
                _command.value = Commands.HideProgress()
            }
            .subscribe({
                groupsLiveData.postValue(it)
            }, {
                it.printStackTrace()
            })
    }
}