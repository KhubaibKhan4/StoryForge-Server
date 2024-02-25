package com.storyforge.plugins.data.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val bio: String?,
    val profileImageUrl: String?,
    val followerCount: Int,
    val followingCount: Int,
    val articleCount: Int
)
