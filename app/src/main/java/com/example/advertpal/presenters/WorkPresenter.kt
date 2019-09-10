package com.example.advertpal.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.advertpal.repositories.VkRepository
import com.example.advertpal.views.WorkActivityView

@InjectViewState
class WorkPresenter(private val repository: VkRepository) : MvpPresenter<WorkActivityView>() {


}
