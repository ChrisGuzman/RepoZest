package com.chris_guzman.repozest.injection

import com.chris_guzman.repozest.network.GitHubApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class NetworkModule(private val baseUri: String) {

    @Provides
    @Reusable
    internal fun provideGitHubApi(retrofit: Retrofit): GitHubApi {
        return  retrofit.create(GitHubApi::class.java)
    }

    @Provides
    @Reusable
    internal fun provideRetrofitInterface(): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(baseUri)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}