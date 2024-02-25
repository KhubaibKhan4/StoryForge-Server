package com.storyforge.plugins.data.dao

import com.storyforge.plugins.data.model.User


interface UserDao {
    suspend fun insert(
         username: String,
         email: String,
         password: String,
         bio: String?,
         profileImageUrl: String?,
         followerCount: Int,
         followingCount: Int,
         articleCount: Int
    ): User?
    suspend fun getAllUserList(): List<User>?
    suspend fun getUserById(id: Int): User?
    suspend fun deleteUserById(id: Int): Int?
    suspend fun updateUser(
        id: Int,
        username: String,
        email: String,
        password: String,
        bio: String?,
        profileImageUrl: String?,
    ): Int?
}