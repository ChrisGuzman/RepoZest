package com.chris_guzman.repozest.ui.repositories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RepoListViewModelFactory(private val org: String): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepoListViewModel(org) as T
    }
}