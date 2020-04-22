package com.chris_guzman.repozest.injection

import com.chris_guzman.repozest.BuildConfig
import com.chris_guzman.repozest.network.GitHubApi
import com.chris_guzman.repozest.network.GitHubApiClient
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class NetworkModule {

    @Provides
    @Reusable
    fun provideRetrofitInterface(): GitHubApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(httpClient.build())
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Reusable
    internal fun provideGitHubClient(gitHubApi: GitHubApi): GitHubApiClient {
        return GitHubApiClient(gitHubApi)
    }
}