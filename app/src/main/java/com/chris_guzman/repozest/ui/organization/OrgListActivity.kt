package com.chris_guzman.repozest.ui.organization

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ActivityOrganizationListBinding
import com.chris_guzman.repozest.ui.repo.RepoListActivity
import com.google.android.material.snackbar.Snackbar

const val EXTRA_ORG_NAME = "extra_org_name"
class OrgListActivity: AppCompatActivity(), OrgCallBack {
    private lateinit var binding: ActivityOrganizationListBinding
    private lateinit var viewModel: OrgListViewModel
    private lateinit var orgListAdapter: OrgListAdapter

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_organization_list)
        viewModel = ViewModelProvider(this).get(OrgListViewModel::class.java)

        orgListAdapter = OrgListAdapter(this)
        binding.orgList.adapter = orgListAdapter
        binding.orgList.layoutManager = LinearLayoutManager(this)

        viewModel.organizations.observe(this, Observer {
            orgListAdapter.updateOrgList(it)
        })

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

    override fun onClick(orgName: String) {
        startActivity(Intent(this, RepoListActivity::class.java).apply {
            putExtra(EXTRA_ORG_NAME, orgName)
        })
    }
}