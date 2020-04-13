package com.chris_guzman.repozest.injection.component

import com.chris_guzman.repozest.injection.NetworkModule
import com.chris_guzman.repozest.ui.repo.RepoListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(repoListViewModel: RepoListViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}