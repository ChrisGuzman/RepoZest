package com.chris_guzman.repozest.ui.organizations

import android.view.View
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.base.BaseListViewModel
import com.chris_guzman.repozest.model.Organization
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OrgListViewModel: BaseListViewModel<Organization>() {

    val errorClickListener = View.OnClickListener { loadOrgs() }

    init {
        loadOrgs()
    }

    private fun loadOrgs(search: String? = null) {
        subscriptions.add( gitHubApiClient.getOrganizations(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .subscribe(
                { onRetrieveSuccess(it)},
                {onRetrieveError(it)}

            ))
    }

    override fun onRetrieveError(error: Throwable) {
        errorMessage.value = R.string.org_list_retrieve_error
    }

}