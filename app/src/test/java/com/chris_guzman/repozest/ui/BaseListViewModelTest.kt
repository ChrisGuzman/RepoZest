package com.chris_guzman.repozest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chris_guzman.repozest.network.GitHubApiClient
import com.chris_guzman.repozest.utils.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class BaseListViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var gitHubApiClient: GitHubApiClient

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}