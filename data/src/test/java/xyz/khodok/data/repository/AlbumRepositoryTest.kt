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
import xyz.khodok.data.mock.album
import xyz.khodok.data.mock.photo
import xyz.khodok.data.repository.album.AlbumRepository
import xyz.khodok.data.source.album.remote.AlbumRemoteDataSourceContract
import xyz.khodok.domain.repository.album.AlbumRepositoryContract

class AlbumRepositoryTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var albumRemoteDataSource: AlbumRemoteDataSourceContract

    private lateinit var albumRepository: AlbumRepositoryContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        albumRepository = AlbumRepository(albumRemoteDataSource)
    }

    @Test
    fun getAlbumByUserId_Success_Test() {
        Mockito.`when`(albumRemoteDataSource.getAlbumByUserId(1))
            .thenReturn(Single.just(listOf(album)))

        albumRepository.getAlbumByUserId(1)

        Mockito.verify(albumRemoteDataSource, Mockito.times(1)).getAlbumByUserId(1)
        Assert.assertNotNull("Album not empty", albumRepository.getAlbumByUserId(1))
    }

    @Test
    fun getFeed_Success_Test() {
        Mockito.`when`(albumRemoteDataSource.getPhotoByAlbumId(1))
            .thenReturn(Single.just(listOf(photo)))

        albumRepository.getPhotoByAlbumId(1)

        Mockito.verify(albumRemoteDataSource, Mockito.times(1)).getPhotoByAlbumId(1)
        Assert.assertNotNull("Photo not empty", albumRepository.getPhotoByAlbumId(1))
    }

}
