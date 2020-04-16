package com.chris_guzman.repozest.ui.organizations

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ActivityOrganizationListBinding
import com.chris_guzman.repozest.ui.repositories.RepoListActivity
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.widget.afterTextChangeEvents
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


const val EXTRA_ORG_NAME = "extra_org_name"
class OrgListActivity: AppCompatActivity(), OrgCallBack {
    private lateinit var binding: ActivityOrganizationListBinding
    private lateinit var viewModel: OrgListViewModel
    private lateinit var orgListAdapter: OrgListAdapter

    private var errorSnackbar: Snackbar? = null
    var subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_organization_list)
        viewModel = ViewModelProvider(this).get(OrgListViewModel::class.java)

        orgListAdapter = OrgListAdapter(this)
        binding.orgList.adapter = orgListAdapter
        binding.orgList.layoutManager = LinearLayoutManager(this)

        viewModel.data.observe(this, Observer {
            orgListAdapter.updateOrgList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            if (it != null) {
                showError(it)
            } else {
                hideError()
            }
        })

        subscriptions.add( binding.orgSearch.afterTextChangeEvents()
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.editable.toString().trim() }
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .switchMap { s -> Observable.just(s) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("GUZ", "search $it")
                    viewModel.loadOrgs(it) },
                { Log.e("GUZ", "error", it)}
            )
        )

        binding.viewModel = viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.dispose()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onClick(orgName: String) {
        startActivity(Intent(this, RepoListActivity::class.java).apply {
            putExtra(EXTRA_ORG_NAME, orgName)
        })
    }
}