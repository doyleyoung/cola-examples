package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.Validable

class NewPostPayload(var title:String?, var content:String?, var categories:List<String>) : Validable {
    override fun isValid(): Boolean {
        return !title.isNullOrEmpty() && !content.isNullOrEmpty()
    }
}