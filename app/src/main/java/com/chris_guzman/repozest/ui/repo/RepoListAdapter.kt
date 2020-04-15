package com.chris_guzman.repozest.ui.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ItemRepositoryBinding
import com.chris_guzman.repozest.model.Repository

class RepoListAdapter(private val callback: RepoCallBack) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    // Can this not be lateinit?
    private lateinit var repoList: List<Repository>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRepositoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repository, parent, false)
        binding.callback = callback
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::repoList.isInitialized) repoList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    fun updateRepoList(repoList: List<Repository>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RepoViewModel()

        fun bind(repository: Repository) {
            viewModel.bind(repository)
            binding.viewModel = viewModel
        }
    }

}