package com.github.bmsantos.cola

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.kittinunf.fuel.core.Manager
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import org.assertj.core.api.Assertions
import java.io.StringWriter
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

data class Post(var title: String? = null, var content: String? = null, var categories: List<String>? = emptyList())

data class PostEntry(var postUuid: String?, var title: String, var content: String, var publishingDate: String, var categories: List<String>)

class Mapper {
    companion object {
        val objectMapper = ObjectMapper().registerModule(KotlinModule())
    }
}

fun Any.json(): String {
    val sw = StringWriter();
    Mapper.objectMapper.writeValue(sw, this)
    return sw.toString();
}

inline fun <reified T> String.jsonTo(): T = Mapper.objectMapper.readValue(this, object : TypeReference<T>() {})

fun Manager.Companion.coalesce() {
    Manager.instance.executor.shutdown();
    try {
        Manager.instance.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        Manager.instance.executor = Executors.newSingleThreadExecutor()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}

fun httpPostBlogPost(post: Post): String? {
    var result: String? = null
    "posts".httpPost().body(post.json()).responseString { request, response, either ->
        val (data, error) = either
        if (data != null) result = data
    }
    Manager.coalesce()
    return result
}

fun httpGetBlogPost(postId: String?): String? {
    var result: String? = null
    "posts/${postId}".httpGet().responseString { request, response, either ->
        val (data, error) = either
        if (data != null) result = data
    }
    Manager.coalesce()
    return result
}

fun httpGetBlogPosts(): String? {
    var result: String? = "[]"
    "posts".httpGet().header("Accept" to "application/json").responseString { request, response, either ->
        val (data, error) = either
        if (data != null) result = data
    }
    Manager.coalesce()
    return result
}

fun httpUpdateBlogPost(postId: String?, post: Post): String? {
    var result: String? = "[]"
    "posts/${postId}".httpPut().body(post.json()).responseString { request, response, either ->
        val (data, error) = either
        if (error != null) result = data
    }
    Manager.coalesce()
    return result
}

fun httpDeleteBlogPost(postId: String?): String? {
    var result: String? = "[]"
    "posts/${postId}".httpDelete().responseString { request, response, either ->
        val (data, error) = either
        if (error != null) result = data
    }
    Manager.coalesce()
    return result
}

fun retrieveExistingPosts(): List<PostEntry>? = httpGetBlogPosts()?.jsonTo()

fun retrieveExistingPost(): PostEntry? {
    val entries: List<PostEntry>? = retrieveExistingPosts()
    Assertions.assertThat(entries).isNotNull()
    Assertions.assertThat(entries).isNotEmpty()
    return entries!!.get(0)
}