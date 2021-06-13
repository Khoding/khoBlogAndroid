package xyz.khodok.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import xyz.khodok.domain.mock.postDetail
import xyz.khodok.domain.repository.post.PostRepositoryContract
import xyz.khodok.domain.usecase.post.PostUseCase
import xyz.khodok.domain.usecase.post.PostUseCaseContract

class PostUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var postRepository: PostRepositoryContract

    private lateinit var postUseCase: PostUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        postUseCase = PostUseCase(postRepository)
    }

    @Test
    fun getPostBySlug_Success_Test() {
        Mockito.`when`(postRepository.getPostBySlug(1))
            .thenReturn(Single.just(postDetail))

        postUseCase.getPostBySlug(1)

        Mockito.verify(postRepository, Mockito.times(1)).getPostBySlug(1)
        Assert.assertNotNull("Post not empty", postUseCase.getPostBySlug(1))
    }

}
