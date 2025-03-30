package com.example.jetpackcomposeapp.testpractice

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    private val testDispatcher = StandardTestDispatcher() // or UnconfinedTestDispatcher()
    private lateinit var apiService: UserService
    private lateinit var repository: UserRepository
    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        apiService = mock()
        repository = UserRepository(apiService)
        viewModel = UserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchUser emits user when api returns success`() = runTest {
        val expectedUser = User(1, "Leanne Graham", "leanne@example.com")
        whenever(apiService.getUser(1)).thenReturn(expectedUser)

        viewModel.fetchUser(1)
        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.userState.first { it != null }
        assertTrue(state!!.isSuccess)
        assertEquals(expectedUser, state.getOrNull())
    }
}

