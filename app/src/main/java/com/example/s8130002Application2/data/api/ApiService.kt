package com.example.s8130002Application2.data.api

import com.example.s8130002Application2.data.model.AuthResponse
import com.example.s8130002Application2.data.model.DashboardResponse
import com.example.s8130002Application2.data.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("footscray/auth")
    suspend fun loginFootscray(@Body request: LoginRequest): Response<AuthResponse>

    @POST("sydney/auth")
    suspend fun loginSydney(@Body request: LoginRequest): Response<AuthResponse>

    @POST("br/auth")
    suspend fun loginBr(@Body request: LoginRequest): Response<AuthResponse>

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(@Path("keypass") keypass: String): Response<DashboardResponse>
}
