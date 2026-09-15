package com.example.s8130002Application2.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s8130002Application2.data.model.Entity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private val _selectedEntity = MutableLiveData<Entity?>()
    val selectedEntity: LiveData<Entity?> = _selectedEntity

    fun setEntity(entity: Entity) {
        _selectedEntity.value = entity
    }
}
