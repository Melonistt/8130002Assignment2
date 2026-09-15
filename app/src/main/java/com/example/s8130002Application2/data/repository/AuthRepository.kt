package com.example.s8130002Application2.data.repository

import com.example.s8130002Application2.data.api.ApiService
import com.example.s8130002Application2.data.model.AuthResponse
import com.example.s8130002Application2.data.model.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun loginFootscray(username: String, password: String): Result<AuthResponse> {
        return try {
            val request = LoginRequest(username, password)
            val response = apiService.loginFootscray(request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginSydney(username: String, password: String): Result<AuthResponse> {
        return try {
            val request = LoginRequest(username, password)
            val response = apiService.loginSydney(request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginBr(username: String, password: String): Result<AuthResponse> {
        return try {
            val request = LoginRequest(username, password)
            val response = apiService.loginBr(request)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
