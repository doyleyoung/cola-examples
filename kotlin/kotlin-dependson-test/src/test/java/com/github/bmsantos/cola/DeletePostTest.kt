package com.github.bmsantos.cola

import cola.ide.BaseColaTest
import com.github.bmsantos.core.cola.story.annotations.*
import com.github.kittinunf.fuel.core.Manager
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

@DependsOn(DropAllPostsTest::class)
class DeletePostTest : BaseColaTest() {

    @Feature
    val feature = """
        Feature: Delete post
          As a user I want to delete posts so that I can deprecate information

        Scenario: Should remove post
          Given a post
          When a delete is submitted
          Then the post will cease to exist
    """

    var originalPost: PostEntry? = null

    @Given("a post")
    @DependsOn(CreateNewPostTest::class)
    fun givenAPost() {
        originalPost = retrieveExistingPost()
    }

    @When("a delete is submitted")
    fun deletePost() {
        httpDeleteBlogPost(originalPost?.postUuid)
    }

    @Then("the post will cease to exist")
    fun verifyPostNoLongerExists() {
        val response = httpGetBlogPost(originalPost?.postUuid)
        assertThat(response).isNull()
    }
}