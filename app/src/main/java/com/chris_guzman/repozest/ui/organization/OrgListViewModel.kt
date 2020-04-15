package com.chris_guzman.repozest.ui.organization

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.base.BaseListViewModel
import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.model.SearchOrgResponse
import com.chris_guzman.repozest.network.GitHubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrgListViewModel: BaseListViewModel() {

    val organizations: MutableLiveData<List<Organization>> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadOrgs() }

    init {
        loadOrgs()
    }

    private fun loadOrgs() {
        subscriptions.add( gitHubApi.getOrganizations("type:org")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .subscribe(
                {onRetrieveOrgListSuccess(it)},
                {onRetrieveError(it)}

            ))
    }

    private fun onRetrieveOrgListSuccess(searchOrgResponse: SearchOrgResponse) {
        organizations.value = searchOrgResponse.items
    }

    override fun onRetrieveError(error: Throwable) {
        errorMessage.value = R.string.org_list_retrieve_error
    }

}