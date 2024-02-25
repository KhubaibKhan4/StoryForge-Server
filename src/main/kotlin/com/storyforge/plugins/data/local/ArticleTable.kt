package com.storyforge.plugins.data.local

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ArticleTable : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val title: Column<String> = varchar("title", length = 500)
    val author: Column<String> = varchar("author", length = 500)
    val content: Column<String> = varchar("content", length = 100000)
    val publishDate: Column<String> = varchar("publishDate", length = 51)
    val tags: Column<String> = varchar("tags", length = 51)
    val comments: Column<String> = varchar("comments", length = 51)
    val claps: Column<Int> = integer("claps")
    val imageUrl: Column<String> = varchar("imageUrl", length = 51)
    val isFeature: Column<Boolean> = bool("isFeature")
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}