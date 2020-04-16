package com.chris_guzman.repozest.ui.repositories

import android.net.Uri
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ActivityRepoListBinding
import com.chris_guzman.repozest.ui.organizations.EXTRA_ORG_NAME
import com.google.android.material.snackbar.Snackbar

class RepoListActivity : AppCompatActivity(), RepoCallBack {
    private lateinit var binding: ActivityRepoListBinding
    private lateinit var viewModel: RepoListViewModel
    private lateinit var repoListAdapter: RepoListAdapter

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val orgName = intent.getStringExtra(EXTRA_ORG_NAME)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)

        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
        viewModel.orgName = orgName

        repoListAdapter = RepoListAdapter(this)

        binding.repoList.adapter = repoListAdapter
        binding.repoList.layoutManager = LinearLayoutManager(this)

        viewModel.data.observe(this, Observer {
            repoListAdapter.updateRepoList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if (errorMessage != null) {
                showError(errorMessage)
            } else {
                hideError()
            }
        })
        binding.viewModel = viewModel
        viewModel.loadRepos()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    override fun onClick(url: String) {
        val customtab = CustomTabsIntent.Builder().build()
        customtab.launchUrl(this, Uri.parse(url))
    }
}