package com.example.s8130002Application2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.s8130002Application2.data.model.DashboardResponse
import com.example.s8130002Application2.data.model.Entity
import com.example.s8130002Application2.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class DashboardUiState {
    object Idle : DashboardUiState()
    object Loading : DashboardUiState()
    data class Success(val dashboardResponse: DashboardResponse) : DashboardUiState()
    data class Error(val message: String) : DashboardUiState()
}

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<DashboardUiState>(DashboardUiState.Idle)
    val uiState: LiveData<DashboardUiState> = _uiState

    private val _selectedEntity = MutableLiveData<Entity?>()
    val selectedEntity: LiveData<Entity?> = _selectedEntity

    fun fetchDashboard(keypass: String) {
        _uiState.value = DashboardUiState.Loading

        viewModelScope.launch {
            val result = dashboardRepository.getDashboard(keypass)

            result.onSuccess { dashboardResponse ->
                _uiState.value = DashboardUiState.Success(dashboardResponse)
            }.onFailure { exception ->
                _uiState.value = DashboardUiState.Error(exception.message ?: "Unknown error occurred")
            }
        }
    }

    fun selectEntity(entity: Entity) {
        _selectedEntity.value = entity
    }
}
