package com.chris_guzman.repozest.network

import android.util.Log
import com.chris_guzman.repozest.model.GitHubResponse
import com.chris_guzman.repozest.model.Organization
import com.chris_guzman.repozest.model.Repository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubApiClient @Inject constructor(private val gitHubApi: GitHubApi) {

    fun getRepos(orgName: String): Observable<GitHubResponse<Repository>> {
        return gitHubApi.getRepos("org:$orgName")
    }

    // /search/users?q={name}+in%3Alogin+type:org
    fun getOrganizations(search: String? = null): Observable<GitHubResponse<Organization>> {
        val query = if (search.isNullOrEmpty()) {
            "type:org"
        } else {
            "$search+in:login+type:org"
        }
        return gitHubApi.getOrganizations(query)
    }
}