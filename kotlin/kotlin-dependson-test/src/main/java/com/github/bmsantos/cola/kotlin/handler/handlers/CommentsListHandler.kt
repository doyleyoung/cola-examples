package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.AbstractRequestHandler
import com.github.bmsantos.cola.kotlin.handler.Answer
import com.github.bmsantos.cola.kotlin.handler.dataToJson
import com.github.bmsantos.cola.kotlin.handler.model.Model
import com.github.bmsantos.cola.kotlin.handler.ok
import j2html.TagCreator.*
import java.util.*

class CommentsListHandler(model: Model) :
        AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, model) {

    override fun processImpl(value: EmptyPayload?, urlParams: Map<String, String>,
                             shouldReturnHtml: Boolean): Answer {
        val uuid = UUID.fromString(urlParams.get(KEY_UUID))

        if (model.getPost(uuid) == null) return Answer(HTTP_BAD_REQUEST)

        if (shouldReturnHtml) {
            val html = body().with(
                    h1("Comments for post " + uuid),
                    div().with(
                            model.getAllCommentsOn(uuid).map {
                                div().with(
                                        h2(it.author),
                                        p(it.content)
                                )
                            }
                    )
            ).render()
            return Answer.ok(html)
        }

        val json = dataToJson(model.getAllCommentsOn(uuid))
        return Answer.ok(json)
    }
}