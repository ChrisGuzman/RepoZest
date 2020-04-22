package com.chris_guzman.repozest.ui.repositories

import com.chris_guzman.repozest.model.GitHubResponse
import com.chris_guzman.repozest.model.Repository
import com.chris_guzman.repozest.ui.BaseListViewModelTest
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mockito

@RunWith(JUnit4::class)
class RepoListViewModelTest: BaseListViewModelTest() {
    @InjectMocks
    var repoListViewModel = RepoListViewModel("NYTimes")

    private var testObservable: Observable<GitHubResponse<Repository>>? = null

    @Test
    fun `when viewmodel calls loadRepos then list of repositories is returned`() {
        val repository = Repository(id = 42, name = "store", full_name = "nytimes/store", html_url = "https://github.com/nytimes/Store", description = "Android Library for Async Data Loading and Caching", stargazers_count = 3600)
        val repoList = listOf(repository)
        val response = GitHubResponse(42, repoList)

        testObservable = Observable.just(response)

        Mockito.`when`(gitHubApiClient.getRepos("NYTimes")).thenReturn(testObservable)
        repoListViewModel.loadRepos()

        assertEquals(1, repoListViewModel.data.value?.size)
    }

    @Test
    fun `when a networking error occurs then the errorMessage is present`() {
        testObservable = Observable.error(Throwable())

        Mockito.`when`(gitHubApiClient.getRepos("NYTimes")).thenReturn(testObservable)

        repoListViewModel.loadRepos()

        assertNotNull(repoListViewModel.errorMessage.value)

    }
}