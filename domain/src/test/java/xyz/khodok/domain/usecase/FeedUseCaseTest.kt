package xyz.khodok.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import xyz.khodok.domain.repository.post.PostRepositoryContract
import xyz.khodok.domain.usecase.feed.FeedUseCase
import xyz.khodok.domain.usecase.feed.FeedUseCaseContract
import xyz.khodok.domain.mock.feed
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FeedUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var postRepository: PostRepositoryContract

    private lateinit var feedUseCase: FeedUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        feedUseCase = FeedUseCase(postRepository)
    }

    @Test
    fun getFeed_Success_Test() {
        Mockito.`when`(postRepository.getAllPosts())
            .thenReturn(Single.just(listOf(feed)))

        feedUseCase.getAllPosts()

        Mockito.verify(postRepository, Mockito.times(1)).getAllPosts()
        Assert.assertNotNull("Feed not empty", feedUseCase.getAllPosts())
    }

}
