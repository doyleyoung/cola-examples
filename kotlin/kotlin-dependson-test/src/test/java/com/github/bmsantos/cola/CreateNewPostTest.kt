package com.github.bmsantos.cola

import cola.ide.BaseColaTest
import com.github.bmsantos.core.cola.story.annotations.*
import org.assertj.core.api.Assertions.assertThat

@DependsOn(DropAllPostsTest::class)
class CreateNewPostTest : BaseColaTest() {

    @Feature
    val feature = """
        Feature: Create post
          As a user I want to create posts so that I can share information

        Scenario: Should add post
          Given a title Foo
          And a content bar
          When a new post is inserted
          Then there will be 1 post
          And the post will have a title of Foo
          And the post will have a content of bar
    """

    var title: String? = null
    var content: String? = null
    var postId: String? = null
    var response: String? = null

    @Given("a title <title>")
    fun givenATitle(@Assigned("title") title: String) {
        this.title = title
    }

    @Given("a content <content>")
    fun givenAContent(@Assigned("content") content: String) {
        this.content = content
    }

    @When("a new post is inserted")
    fun submitPost() {
        postId = httpPostBlogPost(Post(title, content))
    }

    @Then("there will be 1 post")
    fun verifySinglePost() {
        response = httpGetBlogPost(postId) // See Note 1 below

        assertThat(response).contains(postId)
    }

    @Then("the post will have a title of <rxTitle>")
    fun verifyPostTitle(@Assigned("rxTitle") title: String) {
        assertThat(response).contains(this.title);
    }

    @Then("the post will have a content of <rxContent>")
    fun verifyPostContent(@Assigned("rxContent") content: String) {
        assertThat(response).contains(this.content);
    }
}

// Note 1: This example is using in-mem H2 DB and it does not allow for external. So I've "cheated" a wee bit and the
// validations data is being retrieved through the GET request that also needs to be tested. Not a good approach... but
// alright for this example project.