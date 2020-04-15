package com.chris_guzman.repozest.ui.repo

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.base.BaseListViewModel
import com.chris_guzman.repozest.base.BaseViewModel
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.model.Repository
import com.chris_guzman.repozest.model.SearchRepoResponse
import com.chris_guzman.repozest.network.GitHubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel: BaseListViewModel() {

    var orgName: String? = null
    val errorClickListener = View.OnClickListener { loadRepos() }
    val repositories: MutableLiveData<List<Repository>> = MutableLiveData()

    fun loadRepos() {
        val query = if (orgName.isNullOrEmpty()) "org:nytimes" else "org:$orgName"
        subscriptions.add( gitHubApi.getRepos(query = query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .subscribe(
                {onRetrieveRepoListSuccess(it)},
                {onRetrieveError(it)}
            ))
    }

    private fun onRetrieveRepoListSuccess(response: SearchRepoResponse) {
        Log.d("GUZ", "$response")
        repositories.value = response.items
    }

    override fun onRetrieveError(error: Throwable) {
        super.onRetrieveError(error)
        errorMessage.value = R.string.repo_list_retrieve_error
    }
}