package com.chris_guzman.repozest.ui.repo

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.base.BaseViewModel
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
                {onRetrieveRepoListSuccess()},
                {onRetrieveRepoListError(it)}
            )
    }

    private fun onRetrieveRepoListError(error: Throwable) {
        Log.e("GUZ", "error", error)
    }

    private fun onRetrieveRepoListSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onRetrieveRepoListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveRepoListStart() {
        loadingVisibility.value = View.VISIBLE
    }
}