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
class UserRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        userService = mock()
        userRepository = UserRepository(userService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getUser emits success when API call is successful`() = runTest {
        // Arrange
        val expectedUser = User(1, "Shuvo", "shuvo@example.com")
        whenever(userService.getUser(1)).thenReturn(expectedUser)

        // Act
        val result = userRepository.getUser(1).first()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(expectedUser, result.getOrNull())
    }

    @Test
    fun `getUser emits failure when API call throws exception`() = runTest {
        // Arrange
        val exception = RuntimeException("Network error")
        whenever(userService.getUser(1)).thenThrow(exception)

        // Act
        val result = userRepository.getUser(1).first()

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception.message, result.exceptionOrNull()?.message)
    }
}
