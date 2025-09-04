package com.tm.minilogbook.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tm.minilogbook.ui.features.home.components.AverageSection
import com.tm.minilogbook.ui.features.home.components.GlucoseInput
import com.tm.minilogbook.ui.features.home.components.HistoryEmpty
import com.tm.minilogbook.ui.features.home.components.HistoryHeader
import com.tm.minilogbook.ui.features.home.components.HistoryItem
import com.tm.minilogbook.ui.features.home.components.UnitSelection

@Composable
fun HomeScreen(modifier: Modifier, viewModel: HomeViewModel = hiltViewModel()) {

    val state = viewModel.uiState.collectAsState().value

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                item {
                    AverageSection(state.avg)
                    HorizontalDivider()
                }

                item {
                    UnitSelection(
                        selectedUnit = state.selectedUnit,
                        onUnitSelected = viewModel::setUnitType
                    )
                }

                item {
                    GlucoseInput(
                        selectedUnit = state.selectedUnit,
                        onSave = { viewModel.addGlucose(it) }
                    )
                }

                stickyHeader {
                    HistoryHeader()
                    HorizontalDivider()
                }

                if (state.isEmpty) {
                    item {
                        HistoryEmpty()
                    }
                } else {
                    items(state.entries) { entry ->
                        HistoryItem(entry, state.selectedUnit)
                    }
                }
            }
        }
    }
}