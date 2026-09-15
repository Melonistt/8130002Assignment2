package com.example.s8130002Application2.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.s8130002Application2.data.model.AuthResponse
import com.example.s8130002Application2.presentation.viewmodel.LoginViewModel
import com.example.s8130002Application2.presentation.viewmodel.LoginUiState
import com.example.s8130002Application2.data.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var authRepository: AuthRepository
    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        authRepository = mockk()
        loginViewModel = LoginViewModel(authRepository)
    }

    @Test
    fun login_emptyUsername_showsError() = runTest {
        loginViewModel.login("", "password", "footscray")

        val state = loginViewModel.uiState.value
        assert(state is LoginUiState.Error)
    }

    @Test
    fun login_emptyPassword_showsError() = runTest {
        loginViewModel.login("username", "", "footscray")

        val state = loginViewModel.uiState.value
        assert(state is LoginUiState.Error)
    }

    @Test
    fun login_success() = runTest {
        val mockAuthResponse = AuthResponse(keypass = "testKeypass")
        coEvery {
            authRepository.loginFootscray("12345678", "John")
        } returns Result.success(mockAuthResponse)

        loginViewModel.login("12345678", "John", "footscray")

        // Wait a bit for the coroutine to complete
        Thread.sleep(100)

        val state = loginViewModel.uiState.value
        assert(state is LoginUiState.Success)
        assertEquals("testKeypass", (state as LoginUiState.Success).authResponse.keypass)
    }

    @Test
    fun login_failure() = runTest {
        coEvery {
            authRepository.loginFootscray("12345678", "Wrong")
        } returns Result.failure(Exception("Invalid credentials"))

        loginViewModel.login("12345678", "Wrong", "footscray")

        // Wait a bit for the coroutine to complete
        Thread.sleep(100)

        val state = loginViewModel.uiState.value
        assert(state is LoginUiState.Error)
    }
}
