package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.Validable

class EditPostPayload(val title:String?, val content:String?, val categories:List<String>) : Validable {
    override fun isValid(): Boolean {
        return true
    }
}