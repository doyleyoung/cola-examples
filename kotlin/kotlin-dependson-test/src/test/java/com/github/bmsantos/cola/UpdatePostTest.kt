package com.github.bmsantos.cola

import cola.ide.BaseColaTest
import com.github.bmsantos.core.cola.story.annotations.*
import org.assertj.core.api.Assertions.assertThat

@DependsOn(DropAllPostsTest::class)
class UpdatePostTest : BaseColaTest() {

    @Feature
    val feature = """
        @update-post
        @exclude-update-post
        Feature: Update post
          As a user I want to edit posts so that I can update the information

        @update-title
        Scenario: Should update post title
          Given a post
          And a title appended with Beer Rocks!
          When an update is submitted
          Then the post title will be updated
          And the post content will not

        @update-content
        Scenario: Should update post content
          Given a post
          And a content appended with Coffee 4 life!
          When an update is submitted
          Then the post content will be updated
          And the post title will not
    """

    var post: Post = Post()
    var originalPost: PostEntry? = null
    var response: String? = null

    @Given("a post")
    @DependsOn(CreateNewPostTest::class)
    fun givenAPost() {
        originalPost = retrieveExistingPost()
        post.title = originalPost?.title
        post.content = originalPost?.content
    }

    @Given("a <field> appended with <post-fix>")
    fun givenAnPostEntry(@Assigned("field") field:String, @Assigned("post-fix") postfix: String) {
        when (field) {
            "title"   -> post.title = originalPost?.title + " -> " + postfix
            "comment" -> post.content = originalPost?.content + " -> " + postfix
        }
    }

    @When("an update is submitted")
    fun updatePost() {
        httpUpdateBlogPost(originalPost?.postUuid, Post(post.title, post.content, originalPost?.categories))
    }

    @Then("the post title will be updated")
    fun verifyPostTitleWasUpdated() {
        response = httpGetBlogPost(originalPost?.postUuid)

        assertThat(response).contains("\"${post.title}\"")
    }

    @Then("the post content will not")
    fun verifyPostContentWasUnchanged() {
        assertThat(response).contains("\"${originalPost?.content}\"")
    }

    @Then("the post content will be updated")
    fun verifyPostContentWasUpdated() {
        response = httpGetBlogPost(originalPost?.postUuid)

        assertThat(response).contains("\"${post.content}\"")
    }

    @Then("the post title will not")
    fun verifyPostTitleWasUnchanged() {
        assertThat(response).contains("\"${originalPost?.title}\"")
    }
}