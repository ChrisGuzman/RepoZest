package com.chris_guzman.repozest.ui.organizations.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ItemOrganizationBinding
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.ui.organizations.viewmodel.OrgCallBack
import com.chris_guzman.repozest.ui.organizations.viewmodel.OrgViewModel

class OrgListAdapter(
    private val callback: OrgCallBack
) : RecyclerView.Adapter<OrgListAdapter.ViewHolder>() {
    private var orgList: List<Organization> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemOrganizationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_organization, parent, false)
        binding.callback = callback
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return orgList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(orgList[position])
    }

    fun updateOrgList(orgList: List<Organization>) {
        this.orgList = orgList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemOrganizationBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = OrgViewModel()
        fun bind(organization: Organization) {
            viewModel.bind(organization)
            binding.viewModel = viewModel
        }
    }
}