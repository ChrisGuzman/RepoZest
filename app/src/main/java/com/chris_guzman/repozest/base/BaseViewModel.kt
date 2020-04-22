package com.chris_guzman.repozest.base

import androidx.lifecycle.ViewModel
import com.chris_guzman.repozest.injection.component.DaggerApiComponent
import com.chris_guzman.repozest.network.GitHubApiClient
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {
    @Inject
    lateinit var gitHubApiClient: GitHubApiClient

    init {
        DaggerApiComponent.create().inject(this)
    }
}