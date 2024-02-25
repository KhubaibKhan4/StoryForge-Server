package com.storyforge.plugins.routes

import com.storyforge.plugins.domain.repository.ArticleRepository
import com.storyforge.plugins.domain.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.article(
    db: ArticleRepository,
    userDb: UserRepository
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
    put("v1/article/{id}") {
        val id = call.parameters["id"] ?: return@put call.respondText(
            text = "Id Not Found!!",
            status = HttpStatusCode.BadRequest
        )

        val updateInfo = call.receive<Parameters>()
        val title = updateInfo["title"] ?: return@put call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.Unauthorized
        )

        val content = updateInfo["content"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val author = updateInfo["author"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val publishDate = updateInfo["publishDate"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val tags = updateInfo["tags"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val comments = updateInfo["comments"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val claps = updateInfo["claps"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val imageUrl = updateInfo["imageUrl"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        val isFeatured = updateInfo["isFeatured"] ?: return@put call.respondText(
            text = "MISSION FIELD",
            status = HttpStatusCode.Unauthorized
        )
        try {
            val result = id.toInt()
            result.let {
                db.updateArticle(id.toInt(), title, content, tags, imageUrl, isFeatured.toBoolean())
            }
            if (result == 1) {
                call.respondText("Update SuccessFully....", status = HttpStatusCode.OK)
            } else {
                call.respondText("Something Went Wrong", status = HttpStatusCode.BadRequest)
            }

        } catch (e: Throwable) {
            call.respond(status = HttpStatusCode.BadRequest, "Error While Updating Article ${e.message}")
        }
    }

    post("v1/user/create") {
        val parameter = call.receive<Parameters>()
        val username = parameter["username"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val email = parameter["email"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val password = parameter["password"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val bio = parameter["bio"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val profileImageUrl = parameter["profileImageUrl"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val followerCount = parameter["followerCount"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val followingCount = parameter["followingCount"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )
        val articleCount = parameter["articleCount"] ?: return@post call.respondText(
            text = "MISSING FIELD",
            status = HttpStatusCode.BadRequest
        )

        try {
            val user = userDb.insert(
                username = username,
                email = email,
                password = password,
                bio = bio,
                profileImageUrl = profileImageUrl,
                followerCount = followerCount.toInt(),
                followingCount = followingCount.toInt(),
                articleCount = articleCount.toInt(),
            )
            user?.id?.let {
                call.respondText(
                    text = "$email User Created Successfully...",
                    status = HttpStatusCode.OK
                )
            }

        } catch (e: Throwable) {
            call.respondText(
                text = "Error While Creating New User ${e.message}",
                status = HttpStatusCode.Unauthorized
            )
        }
    }

    get("v1/user") {
        try {
            val userList = userDb.getAllUserList()
            if (userList?.isNotEmpty() == true) {
                call.respond(userList)
            }else{
                call.respondText("No User Found....", status = HttpStatusCode.OK)
            }

        } catch (e: Throwable) {
            call.respondText(
                text = "Error While Fetching User List ${e.message}",
                status = HttpStatusCode.Unauthorized
            )
        }
    }

}