package com.storyforge.plugins.data.local

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserTable : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val username: Column<String> = varchar("username", length = 500).uniqueIndex()
    val email: Column<String> = varchar("email", length = 500).uniqueIndex()
    val password: Column<String> = varchar("password", length = 500)
    val bio: Column<String> = varchar("bio", length = 500)
    val profileImageUrl: Column<String> = varchar("profileImageUrl", length = 500)
    val followerCount: Column<Int> = integer("followerCount")
    val followingCount: Column<Int> = integer("followingCount")
    val articleCount: Column<Int> = integer("articleCount")
}