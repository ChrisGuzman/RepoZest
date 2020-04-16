package com.chris_guzman.repozest.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chris_guzman.repozest.ui.organizations.OrgListViewModel
import com.chris_guzman.repozest.ui.repositories.RepoListViewModel

class BaseListViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        RepoListViewModel::class.java -> RepoListViewModel()
        OrgListViewModel::class.java -> OrgListViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}