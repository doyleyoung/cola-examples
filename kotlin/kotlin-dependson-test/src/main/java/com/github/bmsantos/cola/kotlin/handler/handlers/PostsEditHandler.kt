package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.AbstractRequestHandler
import com.github.bmsantos.cola.kotlin.handler.Answer
import com.github.bmsantos.cola.kotlin.handler.model.Model
import java.util.UUID

class PostsEditHandler(model: Model) :
        AbstractRequestHandler<EditPostPayload>(EditPostPayload::class.java, model) {

    override fun processImpl(value: EditPostPayload?, urlParams: Map<String, String>,
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

        val post = model.getPost(uuid)
        if (value?.title != null) post?.title = value?.title
        if (value?.content != null) post?.content = value?.content
        if (value?.categories != null) post?.categories = value?.categories

        model.updatePost(post)
        return Answer(HTTP_OK)
    }
}