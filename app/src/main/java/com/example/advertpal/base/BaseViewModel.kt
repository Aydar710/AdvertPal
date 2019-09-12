package com.example.advertpal.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    val compositDisposable = CompositeDisposable()

    override fun onCleared() {
        compositDisposable.clear()
        super.onCleared()
    }
}