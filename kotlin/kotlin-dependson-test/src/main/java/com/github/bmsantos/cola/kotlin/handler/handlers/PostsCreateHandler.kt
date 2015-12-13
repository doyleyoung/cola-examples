package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.AbstractRequestHandler
import com.github.bmsantos.cola.kotlin.handler.Answer
import com.github.bmsantos.cola.kotlin.handler.model.Model

class PostsCreateHandler(model: Model) :
        AbstractRequestHandler<NewPostPayload>(NewPostPayload::class.java, model) {

    override fun processImpl(value: NewPostPayload?, urlParams: Map<String, String>,
                             shouldReturnHtml: Boolean): Answer {
        val id = model.createPost(value?.title ?: "", value?.content ?: "", value?.categories ?: listOf())
        return Answer(HTTP_CREATED, id.toString())
    }
}