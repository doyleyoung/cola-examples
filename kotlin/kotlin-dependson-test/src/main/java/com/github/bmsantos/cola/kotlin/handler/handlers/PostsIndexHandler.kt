package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.AbstractRequestHandler
import com.github.bmsantos.cola.kotlin.handler.Answer
import com.github.bmsantos.cola.kotlin.handler.dataToJson
import com.github.bmsantos.cola.kotlin.handler.model.Model
import com.github.bmsantos.cola.kotlin.handler.ok
import j2html.TagCreator.*

class PostsIndexHandler(model: Model) :
        AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, model) {

    override fun processImpl(value: EmptyPayload?, urlParams: Map<String, String>, shouldReturnHtml: Boolean): Answer {

        if (shouldReturnHtml) {
            val html = body().with(
                    h1("COLA Tests Blog"),
                    div().with(
                            model.getAllPosts().map {
                                div().with(
                                        h2(it.title),
                                        p(it.content),
                                        ul().with(
                                                it.categories.map { cat -> li(cat) }
                                        )
                                )
                            }
                    )
            ).render()
            return Answer.ok(html)
        }

        val json = dataToJson(model.getAllPosts())
        return Answer.ok(json)
    }
}