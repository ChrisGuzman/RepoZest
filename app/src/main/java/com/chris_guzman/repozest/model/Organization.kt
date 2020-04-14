package com.chris_guzman.repozest.model

data class Organization(
    val id: Int,
    val login: String,
    val avatar_url: String,
    val repos_url: String
)