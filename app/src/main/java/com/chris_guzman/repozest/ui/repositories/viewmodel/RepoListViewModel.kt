package com.chris_guzman.repozest.ui.repositories.viewmodel

import android.view.View
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.base.BaseListViewModel
import com.chris_guzman.repozest.model.GitHubResponse
import com.chris_guzman.repozest.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoListViewModel(private val org: String): BaseListViewModel<Repository>() {

    val errorClickListener = View.OnClickListener { loadRepos() }

    fun loadRepos() {
        subscriptions.add( gitHubApiClient.getRepos(org)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .subscribe(
                {onRetrieveSuccess(it)},
                {onRetrieveError(it)}
            ))
    }

    override fun onRetrieveSuccess(gitHubResponse: GitHubResponse<Repository>) {
        data.value = gitHubResponse.items.take(3)
    }

    override fun onRetrieveError(error: Throwable) {
        super.onRetrieveError(error)
        errorMessage.value = R.string.repo_list_retrieve_error
    }

}