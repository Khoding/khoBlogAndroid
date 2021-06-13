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
import xyz.khodok.domain.usecase.post.PostUseCaseContract
import xyz.khodok.khoblog.mock.postDetail
import xyz.khodok.khoblog.presentation.detail.viewmodel.DetailPostViewModel

class DetailPostViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private var testPostDetail: Single<PostDetail>? = null

    @Mock
    private lateinit var postUseCase: PostUseCaseContract

    private lateinit var postViewModel: DetailPostViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        postViewModel = DetailPostViewModel(postUseCase)
    }

    @Test
    fun getDetailPost_Success_Test() {
        testPostDetail = Single.just(postDetail)
        `when`(postUseCase.getPostById(1)).thenReturn(testPostDetail)
        postViewModel.fetchDetailPost(1)
        Assert.assertEquals(postDetail, postViewModel.post.value)
        Assert.assertEquals(false, postViewModel.isError.value)
        Assert.assertEquals(false, postViewModel.isLoading.value)
    }

    @Test
    fun getDetailPost_ErrorShow_Test() {
        testPostDetail = Single.error(Throwable())
        `when`(postUseCase.getPostById(1)).thenReturn(testPostDetail)
        postViewModel.fetchDetailPost(1)
        Assert.assertEquals(true, postViewModel.isError.value)
    }

    @Test
    fun getDetailPost_LoadingShow_Test() {
        testPostDetail = Single.never()
        `when`(postUseCase.getPostById(1)).thenReturn(testPostDetail)
        postViewModel.fetchDetailPost(1)
        Assert.assertEquals(true, postViewModel.isLoading.value)
    }

}
