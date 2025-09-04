package com.tm.minilogbook.ui.features.home

import com.tm.minilogbook.data.local.GlucoseEntry
import com.tm.minilogbook.domain.model.UnitType

data class HomeUiState(
    val isLoading: Boolean = true,
    val avg: Pair<Double?, UnitType>? = null,
    val selectedUnit: UnitType = UnitType.MG_DL,
    val entries: List<GlucoseEntry> = emptyList(),
) {
    val isEmpty: Boolean get() = !isLoading && entries.isEmpty()
}