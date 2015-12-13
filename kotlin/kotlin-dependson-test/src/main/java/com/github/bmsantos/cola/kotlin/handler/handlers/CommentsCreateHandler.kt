package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.AbstractRequestHandler
import com.github.bmsantos.cola.kotlin.handler.Answer
import com.github.bmsantos.cola.kotlin.handler.model.Model
import com.github.bmsantos.cola.kotlin.handler.ok
import java.util.UUID

class CommentsCreateHandler(model: Model) :
        AbstractRequestHandler<NewCommentPayload>(NewCommentPayload::class.java, model) {

    override fun processImpl(value: NewCommentPayload?, urlParams: Map<String, String>,
                             shouldReturnHtml: Boolean): Answer {
        var uuid = UUID.fromString(urlParams.get(KEY_UUID))

        if (model.existPost(uuid) == null) return Answer(HTTP_BAD_REQUEST)

        val id = model.createComment(uuid, value?.author, value?.content)
        return Answer.ok(id.toString())
   }
}