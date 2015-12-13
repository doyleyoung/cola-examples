package com.github.bmsantos.cola.kotlin.handler.model

import java.util.Date
import java.util.UUID

data class Comment(val commentUuid: UUID, val postUuid:UUID, val author:String,
                   val content:String, val approved:Boolean, val submissionDate:Date) {
}