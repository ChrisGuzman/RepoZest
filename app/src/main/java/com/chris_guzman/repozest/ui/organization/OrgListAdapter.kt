package com.chris_guzman.repozest.ui.organization

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ItemOrganizationBinding
import com.chris_guzman.repozest.model.Organization

class OrgListAdapter: RecyclerView.Adapter<OrgListAdapter.ViewHolder>() {
    private lateinit var orgList: List<Organization>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemOrganizationBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_organization, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::orgList.isInitialized) orgList.size else 0
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