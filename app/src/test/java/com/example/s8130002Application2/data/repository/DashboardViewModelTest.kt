package com.example.s8130002Application2.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.s8130002Application2.data.model.DashboardResponse
import com.example.s8130002Application2.data.model.Entity
import com.example.s8130002Application2.presentation.viewmodel.DashboardViewModel
import com.example.s8130002Application2.presentation.viewmodel.DashboardUiState
import com.example.s8130002Application2.data.repository.DashboardRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dashboardRepository: DashboardRepository
    private lateinit var dashboardViewModel: DashboardViewModel

    @Before
    fun setUp() {
        dashboardRepository = mockk()
        dashboardViewModel = DashboardViewModel(dashboardRepository)
    }

    @Test
    fun fetchDashboard_success() = runTest {
        val mockEntities = listOf(
            Entity("Prop1", "Prop2", "Description 1"),
            Entity("Prop3", "Prop4", "Description 2")
        )
        val mockResponse = DashboardResponse(mockEntities, 2)

        coEvery {
            dashboardRepository.getDashboard("testKeypass")
        } returns Result.success(mockResponse)

        dashboardViewModel.fetchDashboard("testKeypass")

        // Wait a bit for the coroutine to complete
        Thread.sleep(100)

        val state = dashboardViewModel.uiState.value
        assert(state is DashboardUiState.Success)
        assertEquals(2, (state as DashboardUiState.Success).dashboardResponse.entityTotal)
    }

    @Test
    fun fetchDashboard_failure() = runTest {
        coEvery {
            dashboardRepository.getDashboard("invalidKeypass")
        } returns Result.failure(Exception("Failed to fetch"))

        dashboardViewModel.fetchDashboard("invalidKeypass")

        // Wait a bit for the coroutine to complete
        Thread.sleep(100)

        val state = dashboardViewModel.uiState.value
        assert(state is DashboardUiState.Error)
    }

    @Test
    fun selectEntity_setEntity() {
        val entity = Entity("Prop1", "Prop2", "Desc")

        dashboardViewModel.selectEntity(entity)

        assertEquals(entity, dashboardViewModel.selectedEntity.value)
    }
}
