package com.storyforge.plugins

import com.storyforge.plugins.domain.repository.ArticleRepository
import com.storyforge.plugins.domain.repository.DatabaseFactory
import com.storyforge.plugins.routes.article
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    DatabaseFactory.init()
    val db = ArticleRepository()
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    install(AutoHeadResponse)
    routing {
        article(db)
    }
}
