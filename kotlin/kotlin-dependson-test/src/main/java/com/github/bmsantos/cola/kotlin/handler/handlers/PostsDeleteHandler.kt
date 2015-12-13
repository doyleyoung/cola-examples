package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.AbstractRequestHandler
import com.github.bmsantos.cola.kotlin.handler.Answer
import com.github.bmsantos.cola.kotlin.handler.model.Model
import java.util.UUID

class PostsDeleteHandler(model: Model) :
        AbstractRequestHandler<EmptyPayload>(EmptyPayload::class.java, model) {

    override fun processImpl(value: EmptyPayload?, urlParams: Map<String, String>,
                             shouldReturnHtml: Boolean): Answer {
        if (!urlParams.containsKey(KEY_UUID)) {
            throw IllegalArgumentException();
        }

        var uuid: UUID;
        try {
            uuid = UUID.fromString(urlParams.get(KEY_UUID))
        } catch (e:IllegalArgumentException) {
            return Answer(HTTP_NOT_FOUND)
        }

        if (model.getPost(uuid) == null) return Answer(HTTP_NOT_FOUND)

        model.deletePost(uuid)
        return Answer(HTTP_OK)
    }
}