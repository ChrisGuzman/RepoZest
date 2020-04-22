package com.chris_guzman.repozest.network

import com.chris_guzman.repozest.model.GitHubResponse
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("/search/repositories")
    fun getRepos(@Query("q") query: String, @Query("sort") sort: String = "stars"): Observable<GitHubResponse<Repository>>

    @GET("/search/users")
    fun getOrganizations(@Query(value = "q", encoded = true) query: String): Observable<GitHubResponse<Organization>>
}