package com.storyforge.plugins.data.dao

import com.storyforge.plugins.data.model.Article
import com.storyforge.plugins.data.model.Comment

interface ArticleDao {
    suspend fun insert(
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
    ): Article?

    suspend fun getAllArticle(): List<Article>?
    suspend fun getArticleById(id: Int): Article?
    suspend fun deleteArticleById(id: Int): Int?
    suspend fun updateArticle(
        id: Int,
        title: String,
        content: String,
        tags: String,
        imageUrl: String,
        isFeatured: Boolean
    )
}