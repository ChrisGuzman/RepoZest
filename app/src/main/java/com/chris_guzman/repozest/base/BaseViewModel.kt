package com.chris_guzman.repozest.base

import androidx.lifecycle.ViewModel
import com.chris_guzman.repozest.injection.NetworkModule
import com.chris_guzman.repozest.injection.component.DaggerViewModelInjector
import com.chris_guzman.repozest.injection.component.ViewModelInjector
import com.chris_guzman.repozest.ui.repo.RepoListViewModel
import com.chris_guzman.repozest.utils.BASE_URL

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule(BASE_URL))
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is RepoListViewModel -> injector.inject(this)
        }
    }
}