package com.chris_guzman.repozest.network

import com.chris_guzman.repozest.model.SearchOrgResponse
import com.chris_guzman.repozest.model.SearchRepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("/search/repositories")
    fun getRepos(@Query("q") query: String, @Query("sort") sort: String = "stars"): Observable<SearchRepoResponse>
    // /search/users?q={name}+in%3Alogin+type:org
    @GET("/search/users")
    fun getOrganizations(@Query("q") query: String, @Query("sort") sort: String = "repositories"): Observable<SearchOrgResponse>
}