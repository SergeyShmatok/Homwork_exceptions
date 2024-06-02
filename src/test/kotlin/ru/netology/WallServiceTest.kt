package ru.netology

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import ru.netology.WallService.posts
import ru.netology.WallService.comments

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }


    @Test
    fun add_testing() { // проверка без null
        val post = Post(0)
        WallService.add(post)
        assertTrue(posts.last().id != 0)

    }

    @Test
    fun add_testing_with_null() { // проверка с null
        val post = Post(null)
        WallService.add(post)
        assertTrue(posts.last().id != 0)

    }

//----------------------------------------------------

    @Test
    fun update_testing_1() {
        val post1 = Post(null)
        val post2 =
            Post(id = 1, text = "New text") // - пост с изменённым значением text, но с уже имеющимся в массиве id
        WallService.add(post1)

        val result = WallService.update(post2)

        assertEquals(true, result)

    }


    @Test
    fun update_testing_2() {
        val post1 = Post(null)
        val post2 = Post(id = 2, text = "Qwewqeqwe") // - пост с несуществующим id
        WallService.add(post1)

        val result = WallService.update(post2)

        assertEquals(false, result)

    }

    @Test
    fun createComment_add_true() { // - успешное добавление
        val post = Post(0)
        WallService.add(post)
        val testComment = Comment()
        WallService.createComment(1, testComment)
        assertTrue(comments.isNotEmpty())

    }


    @Test(expected = PostNotFoundException::class)  // - выкинет PostNotFoundException
    fun createComment_add_false() {
        val testComment = Comment()
        WallService.createComment(1, testComment)
    }

}