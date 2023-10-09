package com.example.test_1intro.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.test_1intro.MainCoroutineRule
import com.example.test_1intro.getOrAwaitValue
import com.example.test_1intro.repo.FakeArtRepository
import com.example.test_1intro.util.Status
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {
    private lateinit var viewModel: ArtViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun artWithoutYearReturnError(){
        viewModel.makeArt("mono", "lisa", "")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun artWithoutNameReturnError(){
        viewModel.makeArt("", "lisa", "1888")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun artWithoutArtistNameReturnError(){
        viewModel.makeArt("mono", "", "1888")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun artReturnSuccess(){
        viewModel.makeArt("mono", "lisa", "1888")
        val value = viewModel.insertArtMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }
}