package xyz.khodok.khoblog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import xyz.khodok.domain.model.Post
import xyz.khodok.domain.usecase.feed.FeedUseCaseContract
import xyz.khodok.khoblog.mock.feed
import xyz.khodok.khoblog.presentation.feed.viewmodel.FeedViewModel

class FeedViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testFeed: Single<List<Post>>? = null

    @Mock
    private lateinit var feedUseCase: FeedUseCaseContract

    private lateinit var feedViewModel: FeedViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        feedViewModel = FeedViewModel(feedUseCase)
    }

    @Test
    fun getFeed_Success_Test() {
        testFeed = Single.just(listOf(feed))
        `when`(feedUseCase.getAllPosts()).thenReturn(testFeed)
        feedViewModel.fetchFeed()
        Assert.assertEquals(1, feedViewModel.feed.value?.size)
        Assert.assertEquals(false, feedViewModel.isError.value)
        Assert.assertEquals(false, feedViewModel.isLoading.value)
    }

    @Test
    fun getFeed_ErrorShow_Test() {
        testFeed = Single.error(Throwable())
        `when`(feedUseCase.getAllPosts()).thenReturn(testFeed)
        feedViewModel.fetchFeed()
        Assert.assertEquals(true, feedViewModel.isError.value)
    }

    @Test
    fun getFeed_LoadingShow_Test() {
        testFeed = Single.never()
        `when`(feedUseCase.getAllPosts()).thenReturn(testFeed)
        feedViewModel.fetchFeed()
        Assert.assertEquals(true, feedViewModel.isLoading.value)
    }

}
