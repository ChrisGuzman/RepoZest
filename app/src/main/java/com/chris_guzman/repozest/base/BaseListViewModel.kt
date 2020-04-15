package com.chris_guzman.repozest.base

import android.telephony.euicc.DownloadableSubscription
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.chris_guzman.repozest.network.GitHubApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseListViewModel: BaseViewModel() {
    @Inject
    lateinit var gitHubApi: GitHubApi

    var subscriptions = CompositeDisposable()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }

    fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun onRetrieveFinish() {
        loadingVisibility.value = View.GONE
    }

    open fun onRetrieveError(error: Throwable) {
        Log.e("GUZ", "error", error)
    }
}