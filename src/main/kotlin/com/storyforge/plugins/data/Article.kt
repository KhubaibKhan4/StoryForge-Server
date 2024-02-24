package com.storyforge.plugins.data

import kotlinx.serialization.Serializable
import java.sql.Date

@Serializable
data class Article(
    val id: String,
    val title: String,
    val author : String,
    val content: String,
    val publishDate: String,
    val tags: List<String>,
    val comments: List<Comment>,
    val claps: Int,
    val imageUrl: String?,
    val isFeatured: Boolean
)
