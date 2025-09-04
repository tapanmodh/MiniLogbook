package com.tm.minilogbook.ui.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tm.minilogbook.R
import com.tm.minilogbook.domain.model.UnitType
import com.tm.minilogbook.domain.util.formatForDisplay

@Composable
fun AverageSection(avg: Pair<Double?, UnitType>?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        if (avg?.first == null) {
            Text(
                text = stringResource(R.string.no_records),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        } else {
            val (value, selectedUnit) = avg
            Text(
                text = stringResource(
                    R.string.title_average,
                    value?.formatForDisplay() ?: "--",
                    selectedUnit.label
                ),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}