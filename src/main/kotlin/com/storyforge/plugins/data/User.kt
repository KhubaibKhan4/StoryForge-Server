package com.storyforge.plugins.data

data class User(
    val id: String,
    val username: String,
    val email: String,
    val bio: String?,
    val profileImageUrl: String?,
    val followerCount: Int,
    val followingCount: Int,
    val articleCount: Int
)
