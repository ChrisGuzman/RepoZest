package com.chris_guzman.repozest.model

data class GitHubResponse<T>(val total_count: Int, val items: List<T>)
