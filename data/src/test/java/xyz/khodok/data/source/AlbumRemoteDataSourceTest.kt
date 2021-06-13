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
import xyz.khodok.data.mock.album
import xyz.khodok.data.mock.photo
import xyz.khodok.data.scheduler.BaseSchedulerProvider
import xyz.khodok.data.scheduler.SchedulerProvider
import xyz.khodok.data.source.album.remote.AlbumRemoteDataSource
import xyz.khodok.data.source.album.remote.AlbumRemoteDataSourceContract

class AlbumRemoteDataSourceTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var albumService: AlbumService

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var albumRemoteDataSource: AlbumRemoteDataSourceContract

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        schedulerProvider = SchedulerProvider.getInstance()

        albumRemoteDataSource = AlbumRemoteDataSource(
            albumService,
            schedulerProvider
        )
    }

    @Test
    fun getAlbumByUserId_Success_Test() {
        Mockito.`when`(albumService.getAlbumByUserId(1))
            .thenReturn(Single.just(listOf(album)))

        albumRemoteDataSource.getAlbumByUserId(1)

        Mockito.verify(albumService, Mockito.times(1)).getAlbumByUserId(1)
        Assert.assertNotNull("Album not empty", albumRemoteDataSource.getAlbumByUserId(1))
    }

    @Test
    fun getPostBySlug_Success_Test() {
        Mockito.`when`(albumService.getPhotoByAlbumId(1))
            .thenReturn(Single.just(listOf(photo)))

        albumRemoteDataSource.getPhotoByAlbumId(1)

        Mockito.verify(albumService, Mockito.times(1)).getPhotoByAlbumId(1)
        Assert.assertNotNull("Photo not empty", albumRemoteDataSource.getPhotoByAlbumId(1))
    }

}
