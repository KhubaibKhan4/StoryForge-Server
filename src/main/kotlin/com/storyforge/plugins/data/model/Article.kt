package com.storyforge.plugins.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Int,
    val title: String,
    val author : String,
    val content: String,
    val publishDate: String,
    val tags:String,
    val comments: String,
    val claps: Int,
    val imageUrl: String?,
    val isFeatured: Boolean
)
