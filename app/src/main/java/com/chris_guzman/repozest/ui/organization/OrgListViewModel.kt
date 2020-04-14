package com.chris_guzman.repozest.ui.organization

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.model.SearchOrgResponse
import com.chris_guzman.repozest.network.GitHubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrgListViewModel: BaseViewModel() {
    @Inject
    lateinit var gitHubApi: GitHubApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadOrgs() }
    val orgListAdapter: OrgListAdapter = OrgListAdapter()

    init {
        loadOrgs()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadOrgs() {
        subscription = gitHubApi.getOrganizations("type:org")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveOrgListStart() }
            .doOnTerminate { onRetrieveOrgListStop() }
            .subscribe(
                {onRetrieveOrgListSuccess(it)},
                {onRetrieveOrgListError(it)}

            )
    }

    private fun onRetrieveOrgListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveOrgListStop() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveOrgListSuccess(searchOrgResponse: SearchOrgResponse) {
        orgListAdapter.updateOrgList(searchOrgResponse.items)
    }

    private fun onRetrieveOrgListError(error: Throwable) {
        Log.e("GUZ", "error fetching orgs", error)
        errorMessage.value = R.string.org_list_retrieve_error
    }

}