package com.github.bmsantos.cola

import com.github.kittinunf.fuel.core.Manager
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.text.Regex

class DropAllPostsTest {  // See Note 1 below

    @Before
    fun init() {
        Manager.instance.basePath = "http://localhost:4567"
    }

    @Test
    fun shouldDropAllPosts() {
        // Given
        var posts:List<PostEntry>? = retrieveExistingPosts()

        // When
        posts?.forEach {
            httpDeleteBlogPost(it?.postUuid)
        }

        // Then
        posts = retrieveExistingPosts()
        assertThat(posts).isEmpty()
    }

    @Test
    fun foo() {
        println(Regex("\\s|\\n").replace("foo baa\nfdfdf\rgfgf", ""))
    }


}

// Note 1: Because in-mem H2 DB cannot be hit from the outside, this test deletes all posts through a REST call.
// This is a bad test setup because it is the REST interface that it is being put through the acceptance tests.
// A good test env app would do this by accessing the DB directly.