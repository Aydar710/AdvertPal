package com.example.advertpal.features.works

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import androidx.work.WorkManager
import com.example.advertpal.base.BaseViewModel
import com.example.advertpal.base.Commands
import com.example.advertpal.base.SingleLiveEvent
import com.example.advertpal.data.models.works.Work
import com.example.advertpal.data.repositories.FireStoreRepository
import com.example.advertpal.utils.SharedPrefWrapper
import javax.inject.Inject

class WorksViewModel
@Inject constructor(
    private val sPref: SharedPrefWrapper,
    private val repository: FireStoreRepository
) : BaseViewModel() {

    val worksLiveData = MutableLiveData<List<Work>>()

    private val _command: SingleLiveEvent<Commands> = SingleLiveEvent()

    val command: LiveData<Commands>
        get() = _command

    fun addWork(work: Work, userId: String) {
        repository.addWork(work, userId)
    }

    fun getWorks() =
        repository.getWorks(sPref.getUserId())
            .doOnSubscribe {
                compositDisposable.add(it)
                _command.value = Commands.ShowProgress()
            }
            .doFinally {
                _command.value = Commands.HideProgress()
            }
            .subscribe({
                worksLiveData.setValue(it)
                if (it.isEmpty()) {
                    _command.value = Commands.HasNoWorks()
                } else {
                    _command.value = Commands.HasWorks()
                }
            }, {
                it.printStackTrace()
            })

    @SuppressLint("CheckResult")
    fun deleteWork(workId: Long) {
        repository.deleteWork(workId, sPref.getUserId())
            .doOnSubscribe {
                compositDisposable.add(it)
                _command.value = Commands.DeletingWorkInProgress()
            }
            .doOnSuccess {
                _command.value = Commands.WorkDeleted()
            }
            .subscribe({
                if (it) {
                    val changedWorks = mutableListOf<Work>()
                    worksLiveData.value?.let { groups -> changedWorks.addAll(groups) }
                    changedWorks.forEach { work ->
                        if (work.id == workId) {
                            changedWorks.remove(work)
                            return@forEach
                        }
                    }
                    worksLiveData.value = changedWorks
                    WorkManager.getInstance().cancelAllWorkByTag(workId.toString())
                }
            }, {
                it.printStackTrace()
                _command.value = Commands.ErrorWhileDelete()
            })
    }
}