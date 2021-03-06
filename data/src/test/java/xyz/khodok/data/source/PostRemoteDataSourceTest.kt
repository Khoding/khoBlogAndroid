package xyz.khodok.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import xyz.khodok.data.mock.post
import xyz.khodok.data.scheduler.BaseSchedulerProvider
import xyz.khodok.data.scheduler.SchedulerProvider
import xyz.khodok.data.service.PostService
import xyz.khodok.data.source.post.remote.PostRemoteDataSource
import xyz.khodok.data.source.post.remote.PostRemoteDataSourceContract

class PostRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var postService: PostService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var postRemoteDataSource: PostRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        postRemoteDataSource = PostRemoteDataSource(
            postService,
            schedulerProvider
        )
    }

    @Test
    fun getFeed_Success_Test() {
        Mockito.`when`(postService.getAllPosts())
            .thenReturn(Single.just(listOf(post)))

        postRemoteDataSource.getAllPosts()

        Mockito.verify(postService, Mockito.times(1)).getAllPosts()
        Assert.assertNotNull("Feed not empty", postRemoteDataSource.getAllPosts())
    }

    @Test
    fun getPostBySlug_Success_Test() {
        Mockito.`when`(postService.getPostBySlug(1))
            .thenReturn(Single.just(post))

        postRemoteDataSource.getPostBySlug(1)

        Mockito.verify(postService, Mockito.times(1)).getPostBySlug(1)
        Assert.assertNotNull("Post not empty", postRemoteDataSource.getPostBySlug(1))
    }

}
