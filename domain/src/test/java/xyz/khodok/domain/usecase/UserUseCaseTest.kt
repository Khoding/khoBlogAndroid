package xyz.khodok.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import xyz.khodok.domain.repository.user.UserRepositoryContract
import xyz.khodok.domain.usecase.user.UserUseCase
import xyz.khodok.domain.usecase.user.UserUseCaseContract
import xyz.khodok.domain.mock.user
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserUseCaseTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock private lateinit var userRepository: UserRepositoryContract

    private lateinit var userUseCase: UserUseCaseContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userUseCase = UserUseCase(userRepository)
    }

    @Test
    fun getUserById_Success_Test() {
        Mockito.`when`(userRepository.getUserById(1))
            .thenReturn(Single.just(user))

        userUseCase.getUserById(1)

        Mockito.verify(userRepository, Mockito.times(1)).getUserById(1)
        Assert.assertNotNull("User not empty", userUseCase.getUserById(1))
    }

}
