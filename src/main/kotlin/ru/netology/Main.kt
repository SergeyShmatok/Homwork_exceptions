package ru.netology


import java.time.LocalDateTime


data class Post(
    val id: Int?,
    val likes: Likes? = Likes(),
    val text: String = "text",
    val date: LocalDateTime = LocalDateTime.now(),
    val comments: Comments? = Comments(),
    val ownerId: Int = 5,
    val fromId: Int = 5,
    val canPin: Boolean = true,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val attachments: Array<Attachment> = emptyArray(),
)

data class Likes(
    var count: Int = 0,
    val userLikes: Boolean = true,
    val scanLike: Boolean = true,
    val canPublish: Boolean = true
)


data class Comments( // Comments, созданный в прошлых уроках
    var count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = true,
    val canOpen: Boolean = true
)


object WallService {

    var posts = emptyArray<Post>()
    var comments = emptyArray<Comment>()

    private var nextUniqueId: Int = 0

    fun add(post: Post): Post {
        val nextPost = post.copy(
            id = ++nextUniqueId, likes = post.likes?.copy(), comments = post.comments?.copy()
        )
        posts += nextPost
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, postFromPosts) in posts.withIndex())
            if (post.id == postFromPosts.id) {
                posts[index] = post.copy(likes = post.likes?.copy(), comments = post.comments?.copy())
                return true
            }
        return false
    }

    fun clear() {
        posts = emptyArray<Post>()
        nextUniqueId = 0
    }


    fun createComment(postId: Int, comment: Comment): Comment {
        for (postFromPosts in posts)
            if (postId == postFromPosts.id) {
                comment.commentPostID = postFromPosts.id // передача id поста комментарию
                comments += comment
                return comments.last()
            }
        throw PostNotFoundException("Поста с id номер: $postId не существует")
    }
}


data class Comment( // Comment для метода "createComment" и массива "comments"
    val id: Int = 0, // собственный id комментария
    val text: String = "text",
    val fromId: Int = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    val canClose: Boolean = true,
    var commentPostID: Int = 0 // id комментария к посту к которому он относится (ровняется id этого поста)
)


class PostNotFoundException(message: String) : RuntimeException(message) // класс исключения


fun main() {

    val post1 = Post(null)
    val post2 = Post(0)
    val post3 = Post(0)

    WallService.add(post1)
    WallService.add(post2)
    WallService.add(post3)

    println()

    WallService.posts.forEach { println(it) } // печать всех элементов массива

    println()

    val post5 = Post(id = 3, text = "New text")

    WallService.update(post5)

    println()

    WallService.posts.forEach { println(it) }

    println()

    val comment1 = Comment(id = 1, text = "Matrix")

    WallService.createComment(3, comment1)

    println()

    WallService.comments.forEach { println(it) } // - комментарий добавился в comments

    println()


}