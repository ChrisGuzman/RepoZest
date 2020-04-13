package com.chris_guzman.repozest.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris_guzman.repozest.R
import com.chris_guzman.repozest.databinding.ActivityRepoListBinding

class RepoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoListBinding
    private lateinit var viewModel: RepoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_repo_list)
        binding.repoList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(RepoListViewModel::class.java)
        binding.viewModel = viewModel
    }
}