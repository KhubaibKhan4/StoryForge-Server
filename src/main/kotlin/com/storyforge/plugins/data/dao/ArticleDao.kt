package com.storyforge.plugins.data.dao

import com.storyforge.plugins.data.model.Article
import com.storyforge.plugins.data.model.Comment

interface ArticleDao {
    suspend fun insert(
        id: String,
        title: String,
        author: String,
        content: String,
        publishDate: String,
        tags: List<String>,
        comments: List<Comment>,
        claps: Int,
        imageUrl: String?,
        isFeatured: Boolean
    ): Article?

    suspend fun getAllArticle(): List<Article>?
    suspend fun getArticleById(id: Int): Article?
    suspend fun deleteArticleById(id: Int): Int?
    suspend fun updateArticle(
        title: String,
        content: String,
        tags: List<String>,
        imageUrl: String?,
        isFeatured: Boolean
    )
}