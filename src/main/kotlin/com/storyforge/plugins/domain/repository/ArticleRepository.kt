package com.storyforge.plugins.domain.repository

import com.storyforge.plugins.data.dao.ArticleDao
import com.storyforge.plugins.data.local.ArticleTable
import com.storyforge.plugins.data.model.Article
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class ArticleRepository : ArticleDao {
    override suspend fun insert(
        id: Int,
        title: String,
        author: String,
        content: String,
        publishDate: String,
        tags: String,
        comments: String,
        claps: Int,
        imageUrl: String?,
        isFeatured: Boolean
    ): Article? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = ArticleTable.insert { article ->
                article[ArticleTable.title] = title
                article[ArticleTable.author] = author
                article[ArticleTable.content] = content
                article[ArticleTable.publishDate] = publishDate
                article[ArticleTable.tags] = tags
                article[ArticleTable.comments] = comments
                article[ArticleTable.claps] = claps
                article[ArticleTable.imageUrl] = imageUrl!!
                article[ArticleTable.isFeature] = isFeatured
            }
        }
        return rowToResult(statement?.resultedValues?.get(0)!!)

    }

    override suspend fun getAllArticle(): List<Article>? {
        return DatabaseFactory.dbQuery {
            ArticleTable.selectAll().mapNotNull {
                rowToResult(it)
            }
        }
    }

    override suspend fun getArticleById(id: Int): Article? {
        return DatabaseFactory.dbQuery {
            ArticleTable.select { ArticleTable.id.eq(id) }
                .map {
                    rowToResult(it)
                }.singleOrNull()
        }
    }

    override suspend fun deleteArticleById(id: Int): Int? {
        return DatabaseFactory.dbQuery {
            ArticleTable.deleteWhere { ArticleTable.id.eq(id) }
        }
    }

    override suspend fun updateArticle(
        id: Int,
        title: String,
        content: String,
        tags: String,
        imageUrl: String,
        isFeatured: Boolean
    ) {
        DatabaseFactory.dbQuery {
            ArticleTable.update({ ArticleTable.id.eq(id) }) { article ->
                article[ArticleTable.title] = title
                article[ArticleTable.content] = content
                article[ArticleTable.tags] = tags
                article[ArticleTable.imageUrl] = imageUrl
                article[ArticleTable.isFeature] = isFeatured
            }
        }
    }

    private fun rowToResult(row: ResultRow): Article? {
        if (row == null) {
            null
        }
        return Article(
            id = row[ArticleTable.id],
            title = row[ArticleTable.title],
            author = row[ArticleTable.author],
            content = row[ArticleTable.content],
            publishDate = row[ArticleTable.publishDate],
            tags = row[ArticleTable.tags],
            comments = row[ArticleTable.comments],
            claps = row[ArticleTable.claps],
            imageUrl = row[ArticleTable.imageUrl],
            isFeatured = row[ArticleTable.isFeature]
        )
    }
}