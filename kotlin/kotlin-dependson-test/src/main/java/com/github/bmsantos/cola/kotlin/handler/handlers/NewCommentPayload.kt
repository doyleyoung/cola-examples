package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.Validable

class NewCommentPayload(var author:String, var content:String) : Validable {
    override fun isValid(): Boolean {
        return !author.isNullOrEmpty() && !content.isNullOrEmpty()
    }
}