package com.chris_guzman.repozest.injection.component

import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.injection.NetworkModule
import com.chris_guzman.repozest.network.GitHubApiClient
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApiComponent {
    fun inject(viewModel: BaseViewModel)
    fun inject(gitHubApiClient: GitHubApiClient)
}