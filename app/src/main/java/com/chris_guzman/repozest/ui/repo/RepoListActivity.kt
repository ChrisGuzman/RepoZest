package com.chris_guzman.repozest.ui.repo

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ActivityRepoListBinding
import com.google.android.material.snackbar.Snackbar

class RepoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoListBinding
    private lateinit var viewModel: RepoListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)
        binding.repoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if (errorMessage != null) {
                showError(errorMessage)
            } else {
                hideError()
            }
        })
        binding.viewModel = viewModel
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }
}