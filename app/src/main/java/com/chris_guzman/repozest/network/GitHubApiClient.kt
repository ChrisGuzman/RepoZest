package com.chris_guzman.repozest.network

import com.chris_guzman.repozest.injection.component.DaggerApiComponent
import com.chris_guzman.repozest.model.GitHubResponse
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.model.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubApiClient {

    @Inject
    lateinit var gitHubApi: GitHubApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getRepos(orgName: String): Observable<GitHubResponse<Repository>> {
        return gitHubApi.getRepos("org:$orgName")
    }

    fun getOrganizations(search: String? = null): Observable<GitHubResponse<Organization>> {
        val query = if (search.isNullOrEmpty()) {
            "type:org"
        } else {
            "$search+in:login+type:org"
        }
        return gitHubApi.getOrganizations(query)
    }
}