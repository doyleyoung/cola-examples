package com.github.bmsantos.cola.kotlin.handler.handlers

import com.github.bmsantos.cola.kotlin.handler.Validable

class EmptyPayload : Validable {
    override fun isValid(): Boolean {
        return true
    }
}