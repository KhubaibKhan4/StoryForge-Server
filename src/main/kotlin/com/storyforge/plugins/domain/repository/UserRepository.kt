package com.storyforge.plugins.domain.repository

import com.storyforge.plugins.data.dao.UserDao
import com.storyforge.plugins.data.local.ArticleTable
import com.storyforge.plugins.data.local.UserTable
import com.storyforge.plugins.data.model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserRepository : UserDao {
    override suspend fun insert(
        username: String,
        email: String,
        password: String,
        bio: String?,
        profileImageUrl: String?,
        followerCount: Int,
        followingCount: Int,
        articleCount: Int
    ): User? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = UserTable.insert { user ->
                user[UserTable.username] = username
                user[UserTable.email] = email
                user[UserTable.password] = password
                user[UserTable.bio] = bio!!
                user[UserTable.profileImageUrl] = profileImageUrl!!
                user[UserTable.followerCount] = followerCount
                user[UserTable.followingCount] = followingCount
                user[UserTable.articleCount] = articleCount
            }
        }
        return rowToUser(statement?.resultedValues?.get(0)!!)
    }

    override suspend fun getAllUserList(): List<User>? {
        return DatabaseFactory.dbQuery {
            UserTable.selectAll()
                .mapNotNull {
                    rowToUser(it)
                }
        }
    }

    override suspend fun getUserById(id: Int): User? {
        return DatabaseFactory.dbQuery {
            UserTable.select {
                ArticleTable.id.eq(id)
            }.map {
                rowToUser(it)
            }.singleOrNull()
        }
    }

    override suspend fun deleteUserById(id: Int): Int? {
        return DatabaseFactory.dbQuery {
            UserTable.deleteWhere {
                UserTable.id.eq(id)
            }
        }
    }

    override suspend fun updateUser(
        id: Int,
        username: String,
        email: String,
        password: String,
        bio: String?,
        profileImageUrl: String?
    ): Int? {
        return DatabaseFactory.dbQuery {
            UserTable.update({ UserTable.id.eq(id) }) { user ->
                user[UserTable.id] = id
                user[UserTable.username] = username
                user[UserTable.email] = email
                user[UserTable.password] = password
                user[UserTable.bio] = bio!!
                user[UserTable.profileImageUrl] = profileImageUrl!!
            }
        }
    }

    fun rowToUser(row: ResultRow): User? {
        if (row == null) {
            return null
        }
        return User(
            id = row[UserTable.id],
            username = row[UserTable.username],
            email = row[UserTable.email],
            password = row[UserTable.password],
            bio = row[UserTable.bio],
            profileImageUrl = row[UserTable.profileImageUrl],
            followerCount = row[UserTable.followerCount],
            followingCount = row[UserTable.followingCount],
            articleCount = row[UserTable.articleCount]
        )
    }
}