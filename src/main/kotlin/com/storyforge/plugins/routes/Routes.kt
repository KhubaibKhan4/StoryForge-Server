package com.storyforge.plugins.routes

import com.storyforge.plugins.domain.repository.ArticleRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.article(
    db: ArticleRepository
) {
    post("v1/article") {
        val parameters = call.receive<Parameters>()
        val title = parameters["title"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val content = parameters["content"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val author = parameters["author"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val publishDate = parameters["publishDate"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val tags = parameters["tags"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val comments = parameters["comments"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val claps = parameters["claps"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val imageUrl = parameters["imageUrl"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val isFeatured = parameters["isFeatured"] ?: return@post call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        try {
            val article = db.insert(
                title = title,
                content = content,
                author = author,
                publishDate = publishDate,
                tags = tags,
                claps = claps.toInt(),
                comments = comments,
                isFeatured = isFeatured.toBoolean(),
                imageUrl = imageUrl
            )
            article.let {
                call.respond(status = HttpStatusCode.OK, message = "Uploaded to Server Successfully...")
            }

        } catch (e: Throwable) {
            call.respondText(
                "Getting Error While Posting Data to Server : ${e.message}",
                status = HttpStatusCode.BadRequest
            )
        }

    }
    get("v1/article") {
        try {
            val articleList = db.getAllArticle()
            if (articleList?.isNotEmpty() == true) {
                call.respond(articleList)
            } else {
                call.respondText("No Article Found....", status = HttpStatusCode.OK)
            }
        } catch (e: Throwable) {
            call.respond(status = HttpStatusCode.BadRequest, "Getting Articles Error")
        }
    }
    get("v1/article/{id}") {
        val parameter = call.parameters["id"]
        try {
            val article = parameter?.toInt()?.let { id ->
                db.getArticleById(id)
            } ?: return@get call.respondText(
                text = "Invalid ID",
                status = HttpStatusCode.BadRequest
            )
            article.id.let {
                call.respond(status = HttpStatusCode.OK, article)
            }

        } catch (e: Throwable) {
            call.respond(status = HttpStatusCode.BadRequest, "Error While Fetching Article ${e.message}")
        }
    }
    delete("v1/article/{id}") {
        val parameter = call.parameters["id"]
        try {
            val article = parameter?.toInt()?.let { id ->
                db.deleteArticleById(id)
            } ?: return@delete call.respondText(
                text = "No Id Found...",
                status = HttpStatusCode.BadRequest
            )

            if (article == 1) {
                call.respondText(
                    text = "Deleted Successfully",
                    status = HttpStatusCode.OK
                )
            } else {
                call.respondText(text = "Id Not Found", status = HttpStatusCode.BadRequest)
            }

        } catch (e: Throwable) {
            call.respond(status = HttpStatusCode.BadRequest, "Error While Deleting Article ${e.message}")
        }
    }
}