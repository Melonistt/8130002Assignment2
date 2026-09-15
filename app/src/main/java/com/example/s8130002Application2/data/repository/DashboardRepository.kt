package com.example.s8130002Application2.data.repository

import com.example.s8130002Application2.data.api.ApiService
import com.example.s8130002Application2.data.model.DashboardResponse
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getDashboard(keypass: String): Result<DashboardResponse> {
        return try {
            android.util.Log.d("DashboardRepository", "Fetching dashboard with keypass: $keypass")

            val response = apiService.getDashboard(keypass)

            android.util.Log.d("DashboardRepository", "Response code: ${response.code()}")
            android.util.Log.d("DashboardRepository", "Response message: ${response.message()}")
            android.util.Log.d("DashboardRepository", "Is successful: ${response.isSuccessful}")
            android.util.Log.d("DashboardRepository", "Response body: ${response.body()}")

            if (!response.isSuccessful) {
                val errorBody = response.errorBody()?.string()
                android.util.Log.e("DashboardRepository", "Error body: $errorBody")
            }

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch dashboard: ${response.message()}"))
            }
        } catch (e: Exception) {
            android.util.Log.e("DashboardRepository", "Exception caught: ${e.message}", e)
            Result.failure(e)
        }
    }

}

