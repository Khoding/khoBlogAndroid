package xyz.khodok.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import xyz.khodok.data.mock.user
import xyz.khodok.data.repository.user.UserRepository
import xyz.khodok.data.source.user.remote.UserRemoteDataSourceContract
import xyz.khodok.domain.repository.user.UserRepositoryContract

class UserRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRemoteDataSource: UserRemoteDataSourceContract

    private lateinit var userRepository: UserRepositoryContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepository(userRemoteDataSource)
    }

    @Test
    fun getUserByUserId_Success_Test() {
        Mockito.`when`(userRemoteDataSource.getUserById(1))
            .thenReturn(Single.just(user))

        userRepository.getUserById(1)

        Mockito.verify(userRemoteDataSource, Mockito.times(1)).getUserById(1)
        Assert.assertNotNull("User not empty", userRepository.getUserById(1))
    }

}
