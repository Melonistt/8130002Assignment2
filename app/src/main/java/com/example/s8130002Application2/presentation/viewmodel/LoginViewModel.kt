package com.example.s8130002Application2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8130002Application2.data.model.AuthResponse
import com.example.s8130002Application2.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val authResponse: AuthResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val uiState: LiveData<LoginUiState> = _uiState

    fun login(username: String, password: String, endpoint: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _uiState.value = LoginUiState.Error("Please fill in all fields")
            return
        }

        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            val result = when (endpoint) {
                "footscray" -> authRepository.loginFootscray(username, password)
                "sydney" -> authRepository.loginSydney(username, password)
                "br" -> authRepository.loginBr(username, password)
                else -> Result.failure(Exception("Invalid endpoint"))
            }

            result.onSuccess { authResponse ->
                _uiState.value = LoginUiState.Success(authResponse)
            }.onFailure { exception ->
                _uiState.value = LoginUiState.Error(exception.message ?: "Unknown error occurred")
            }
        }
    }
}
