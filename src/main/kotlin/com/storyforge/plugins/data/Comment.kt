package com.storyforge.plugins.data

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val id: String,
    val author: String,
    val content: String,
    val publishDate: String
)
