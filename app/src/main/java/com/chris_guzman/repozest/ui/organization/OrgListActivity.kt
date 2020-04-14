package com.chris_guzman.repozest.ui.organization

import android.os.Bundle
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ActivityOrganizationListBinding
import com.google.android.material.snackbar.Snackbar

class OrgListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityOrganizationListBinding
    private lateinit var viewModel: OrgListViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_organization_list)
        binding.orgList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(OrgListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            if (it != null) {
                showError(it)
            } else {
                hideError()
            }
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}