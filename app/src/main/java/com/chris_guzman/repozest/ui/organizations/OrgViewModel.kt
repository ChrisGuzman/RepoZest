package com.chris_guzman.repozest.ui.organizations

import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.model.Organization

class OrgViewModel: BaseViewModel() {
    val name = MutableLiveData<String>()
    val logo = MutableLiveData<String>()

    fun bind(organization: Organization) {
        name.value = organization.login
        logo.value = organization.avatar_url
    }
}