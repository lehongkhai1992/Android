package com.example.test_1intro.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.example.test_1intro.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: ArtDatabase

    private lateinit var dao: ArtDao


    @Before
    fun setup(){
/*        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), ArtDatabase::class.java
        ).allowMainThreadQueries().build()*/
        hiltRule.inject()
        dao = database.artDao()
    }

    @Test
    fun insertArtTesting() = runBlocking {
        val art = Art("lisa", "vini", 1700, "test.com", 1)
        dao.insertArt(art)
        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(art)
    }

    @Test
    fun deleteArtTesting() = runBlocking {
        val art1 = Art("lisa1", "vini1", 1700, "test.com", 1)
        val art2 = Art("lisa2", "vini2", 1800, "test.com", 2)
        dao.insertArt(art1)
        dao.insertArt(art2)
        dao.deleteArt(art1)
        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(art1)
    }

    @After
    fun teardown(){
        database.close()
    }
}