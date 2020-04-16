package com.chris_guzman.repozest.ui.repositories

import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.model.Repository

class RepoViewModel: BaseViewModel() {
    var name = MutableLiveData<String>()
        private set
    var description = MutableLiveData<String>()
        private set
    var url = MutableLiveData<String>()
        private set

    fun bind(repository: Repository) {
        name.value = repository.name
        description.value = repository.description
        url.value = repository.html_url
    }
}