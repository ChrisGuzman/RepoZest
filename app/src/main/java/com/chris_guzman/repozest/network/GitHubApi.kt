package com.chris_guzman.repozest.network

import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.model.Repository
import com.chris_guzman.repozest.model.SearchRepoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("orgs/{organization}")
    fun getOrganization(@Path("organization") organization: String): Observable<Organization>

    @GET("/search/repositories")
    fun getRepos(@Query("q") query: String, @Query("sort") sort: String = "stars"): Observable<SearchRepoResponse>
}