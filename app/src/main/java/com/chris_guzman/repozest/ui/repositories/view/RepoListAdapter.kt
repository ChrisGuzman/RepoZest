package com.chris_guzman.repozest.ui.repositories.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ItemRepositoryBinding
import com.chris_guzman.repozest.model.Repository
import com.chris_guzman.repozest.ui.repositories.viewmodel.RepoCallBack
import com.chris_guzman.repozest.ui.repositories.viewmodel.RepoViewModel

class RepoListAdapter(private val callback: RepoCallBack) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {
    private var repoList: List<Repository> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemRepositoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_repository, parent, false)
        binding.callback = callback
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoList.size
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