package com.chris_guzman.repozest.base

import androidx.lifecycle.ViewModel
import com.chris_guzman.repozest.injection.NetworkModule
import com.chris_guzman.repozest.injection.component.DaggerViewModelInjector
import com.chris_guzman.repozest.injection.component.ViewModelInjector
import com.chris_guzman.repozest.ui.organizations.OrgListViewModel
import com.chris_guzman.repozest.ui.repositories.RepoListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule())
        .build()

    init {
        inject()
    }

    private fun inject() {
        when(this) {
            is RepoListViewModel -> injector.inject(this)
            is OrgListViewModel -> injector.inject(this)
        }
    }
}