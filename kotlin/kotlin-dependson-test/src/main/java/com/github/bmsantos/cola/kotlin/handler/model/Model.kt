package com.github.bmsantos.cola.kotlin.handler.model

import java.util.*

interface Model {
    fun createPost(title: String, content: String, categories: List<String>): UUID
    fun createComment(post: UUID, author: String?, content: String?): UUID
    fun getAllPosts(): List<Post>
    fun getAllCommentsOn(post: UUID): List<Comment>
    fun existPost(post: UUID): Boolean
    fun getPost(post: UUID): Post?
    fun updatePost(post: Post?)
    fun deletePost(post: UUID)
}