package com.example.s8130002Application2.data.repository

import com.example.s8130002Application2.data.api.ApiService
import com.example.s8130002Application2.data.model.AuthResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AuthRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        apiService = mockk()
        authRepository = AuthRepository(apiService)
    }

    @Test
    fun loginFootscray_success() = runTest {
        val mockResponse = AuthResponse(keypass = "testKeypass123")
        coEvery {
            apiService.loginFootscray(any())
        } returns Response.success(mockResponse)

        val result = authRepository.loginFootscray("12345678", "John")

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals("testKeypass123", result.getOrNull()?.keypass)
    }

    @Test
    fun loginFootscray_failure() = runTest {
        coEvery {
            apiService.loginFootscray(any())
        } returns Response.error(401, mockk())

        val result = authRepository.loginFootscray("12345678", "WrongPassword")

        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun loginSydney_success() = runTest {
        val mockResponse = AuthResponse(keypass = "sydneyKeypass")
        coEvery {
            apiService.loginSydney(any())
        } returns Response.success(mockResponse)

        val result = authRepository.loginSydney("12345678", "Jane")

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals("sydneyKeypass", result.getOrNull()?.keypass)
    }

    @Test
    fun loginBr_success() = runTest {
        val mockResponse = AuthResponse(keypass = "brKeypass")
        coEvery {
            apiService.loginBr(any())
        } returns Response.success(mockResponse)

        val result = authRepository.loginBr("12345678", "Test")

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals("brKeypass", result.getOrNull()?.keypass)
    }
}