package com.github.bmsantos.cola.kotlin.handler

data class Answer(val code:Int, val body:String = "") {
    companion object {}
}

fun Answer.Companion.ok(body:String):Answer {
    return Answer(200, body)
}