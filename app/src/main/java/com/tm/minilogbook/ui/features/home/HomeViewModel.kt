package com.tm.minilogbook.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tm.minilogbook.data.repository.GlucoseRepository
import com.tm.minilogbook.domain.model.UnitType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: GlucoseRepository
) : ViewModel() {

    private val _uiState = repo.allEntriesFlow
        .combine(repo.averageDisplayFlow) { entries, avg ->
            entries to avg
        }
        .combine(repo.unitTypeFlow) { (entries, avg), unit ->
            HomeUiState(
                isLoading = false,
                avg = avg,
                selectedUnit = unit,
                entries = entries
            )
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, HomeUiState())

    val uiState = _uiState

    fun addGlucose(valueMgDl: Double) {
        viewModelScope.launch {
            repo.addGlucose(valueMgDl)
        }
    }

    fun setUnitType(type: UnitType) {
        viewModelScope.launch {
            repo.setUnitType(type)
        }
    }
}