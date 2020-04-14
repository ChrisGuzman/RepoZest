package com.chris_guzman.repozest.ui.repo

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.model.SearchRepoResponse
import com.chris_guzman.repozest.network.GitHubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel: BaseViewModel() {
    @Inject
    lateinit var gitHubApi: GitHubApi

    private lateinit var subscription: Disposable
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadRepos() }
    val repoListAdapter: RepoListAdapter = RepoListAdapter()

    init {
        loadRepos()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadRepos() {
        subscription = gitHubApi.getRepos(query = "org:nytimes")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveRepoListStart() }
            .doOnTerminate { onRetrieveRepoListFinish() }
            .subscribe(
                {onRetrieveRepoListSuccess(it)},
                {onRetrieveRepoListError(it)}
            )
    }

    private fun onRetrieveRepoListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveRepoListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveRepoListSuccess(response: SearchRepoResponse) {
        repoListAdapter.updateRepoList(response.items)
    }

    private fun onRetrieveRepoListError(error: Throwable) {
        Log.e("GUZ", "error", error)
        errorMessage.value = R.string.repo_list_retrieve_error
    }

}