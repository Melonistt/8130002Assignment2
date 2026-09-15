package com.example.s8130002Application2.data.repository

import com.example.s8130002Application2.data.api.ApiService
import com.example.s8130002Application2.data.model.DashboardResponse
import com.example.s8130002Application2.data.model.Entity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class DashboardRepositoryTest {

    private lateinit var apiService: ApiService
    private lateinit var dashboardRepository: DashboardRepository

    @Before
    fun setUp() {
        apiService = mockk()
        dashboardRepository = DashboardRepository(apiService)
    }

    @Test
    fun getDashboard_success() = runTest {
        val mockEntities = listOf(
            Entity(
                property1 = "Value1",
                property2 = "Value2",
                description = "Test Description"
            ),
            Entity(
                property1 = "Value3",
                property2 = "Value4",
                description = "Another Description"
            )
        )
        val mockResponse = DashboardResponse(
            entities = mockEntities,
            entityTotal = 2
        )

        coEvery {
            apiService.getDashboard("testKeypass")
        } returns Response.success(mockResponse)

        val result = dashboardRepository.getDashboard("testKeypass")

        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrNull()?.entityTotal)
        assertEquals(2, result.getOrNull()?.entities?.size)
    }

    @Test
    fun getDashboard_failure() = runTest {
        coEvery {
            apiService.getDashboard("invalidKeypass")
        } returns Response.error(404, mockk())

        val result = dashboardRepository.getDashboard("invalidKeypass")

        assertTrue(result.isFailure)
    }

    @Test
    fun getDashboard_emptyList() = runTest {
        val mockResponse = DashboardResponse(
            entities = emptyList(),
            entityTotal = 0
        )

        coEvery {
            apiService.getDashboard("emptyKeypass")
        } returns Response.success(mockResponse)

        val result = dashboardRepository.getDashboard("emptyKeypass")

        assertTrue(result.isSuccess)
        assertEquals(0, result.getOrNull()?.entityTotal)
        assertTrue(result.getOrNull()?.entities?.isEmpty() ?: false)
    }
}
