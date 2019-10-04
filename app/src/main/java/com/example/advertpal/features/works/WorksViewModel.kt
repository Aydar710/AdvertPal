package com.example.advertpal.features.works

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import androidx.work.WorkManager
import com.example.advertpal.base.BaseViewModel
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

    fun addWork(work: Work, userId: String) {
        repository.addWork(work, userId)
    }

    fun getWorks(userId: String) =
        repository.getWorks(userId)
            .doOnSubscribe {
                compositDisposable.add(it)
            }
            .subscribe({
                worksLiveData.setValue(it)
            }, {
                it.printStackTrace()
            })

    @SuppressLint("CheckResult")
    fun deleteWork(workId: Long) {
        //TODO: Correct
        sPref.saveUserId("116812347")
        repository.deleteWork(workId, sPref.getUserId())
            .doOnSubscribe {
                compositDisposable.add(it)
            }
            .subscribe({
                if (it) {
                    val changedWorks = mutableListOf<Work>()
                    worksLiveData.value?.let { it1 -> changedWorks.addAll(it1) }
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
            })
    }
}