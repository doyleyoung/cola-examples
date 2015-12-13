package com.github.bmsantos.cola.kotlin.handler.model

import java.util.*

data class Post(val postUuid: UUID, var title:String, var content:String,
                val publishingDate:Date, var categories:List<String>) {
}