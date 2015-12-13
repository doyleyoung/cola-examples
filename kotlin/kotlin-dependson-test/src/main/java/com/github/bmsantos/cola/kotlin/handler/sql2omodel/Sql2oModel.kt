package com.github.bmsantos.cola.kotlin.handler.sql2omodel

import com.github.bmsantos.cola.kotlin.handler.model.Comment
import com.github.bmsantos.cola.kotlin.handler.model.Model
import com.github.bmsantos.cola.kotlin.handler.model.Post
import org.sql2o.Connection
import org.sql2o.Sql2o
import java.util.*

class Sql2oModel(val sql2o: Sql2o) : Model {

    override fun createPost(title: String, content: String, categories: List<String>): UUID {
        sql2o.beginTransaction().use {
            try {
                val postUuid = UUID.randomUUID()
                it.apply {
                    createQuery("insert into posts(postUuid, title, content, publishingDate) VALUES (:post_uuid, :title, :content, :date)")
                            .addParameter("post_uuid", postUuid)
                            .addParameter("title", title)
                            .addParameter("content", content)
                            .addParameter("date", Date())
                            .executeUpdate()
                    categories.forEach {
                        createQuery("insert into posts_categories(postUuid, category) VALUES (:post_uuid, :category)")
                                .addParameter("post_uuid", postUuid)
                                .addParameter("category", it)
                                .executeUpdate()
                    }
                    commit()
                }
                return postUuid
            } finally {
                it.close()
            }
        }
    }

    override fun createComment(post: UUID, author: String?, content: String?): UUID {
        sql2o.open().use {
            val commentUuid = UUID.randomUUID()
            it.apply {
                createQuery("insert into comments(commentUuid, postUuid, author, content, approved, submissionDate) VALUES (:comment_uuid, :post_uuid, :author, :content, :approved, :date)")
                        .addParameter("comment_uuid", commentUuid)
                        .addParameter("post_uuid", post)
                        .addParameter("author", author)
                        .addParameter("content", content)
                        .addParameter("approved", false)
                        .addParameter("date", Date())
                        .executeUpdate();
            }
            return commentUuid
        }
    }

    override fun getAllPosts(): MutableList<Post> {
        sql2o.open().use {
            val posts = it.createQuery("select * from posts").executeAndFetch(Post::class.java);
            posts.forEach { post ->
                post.categories = getCategoriesFor(it, post.postUuid);
            }
            return posts
        }
    }

    override fun getAllCommentsOn(post: UUID): List<Comment> {
        sql2o.open().use {
            return it.createQuery("select * from comments where postUuid=:post_uuid")
                    .addParameter("post_uuid", post)
                    .executeAndFetch(Comment::class.java);
        }
    }

    override fun existPost(post: UUID): Boolean {
        sql2o.open().use {
            val posts = it.createQuery("select * from posts where postUuid=:post")
                    .addParameter("post", post)
                    .executeAndFetch(Post::class.java);
            return posts.size > 0;
        }
    }

    override fun getPost(post: UUID): Post? {
        sql2o.open().use {
            val posts = it.createQuery("select * from posts where postUuid=:post_uuid")
                    .addParameter("post_uuid", post)
                    .executeAndFetch(Post::class.java);

            if (posts.size == 0) {
                return null;
            } else if (posts.size == 1) {
                return posts.get(0);
            }

            throw RuntimeException();
        }
    }

    override fun updatePost(post: Post?) {
        if (post == null) return
        sql2o.open().use {
            it.createQuery("update posts set title=:title, content=:content where postUuid=:post_uuid")
                    .addParameter("post_uuid", post.postUuid)
                    .addParameter("title", post.title)
                    .addParameter("content", post.content)
                    .executeUpdate();
        }
    }

    override fun deletePost(post: UUID) {
        sql2o.open().use {
            it.createQuery("delete from posts where postUuid=:post_uuid")
                    .addParameter("post_uuid", post)
                    .executeUpdate();
        }
    }

    private fun getCategoriesFor(conn: Connection, post_uuid: UUID): List<String> {
        return conn.createQuery("select category from posts_categories where postUuid=:post_uuid")
                .addParameter("post_uuid", post_uuid)
                .executeAndFetch(String::class.java);
    }
}

public inline fun <T : AutoCloseable, R> T.use(block: (T) -> R): R {
    var closed = false
    try {
        return block(this)
    } catch (e: Exception) {
        closed = true
        try {
            this.close()
        } catch (closeException: Exception) {
        }
        throw e
    } finally {
        if (!closed) {
            this.close()
        }
    }
}