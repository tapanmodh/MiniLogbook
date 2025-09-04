package com.tm.minilogbook.ui.features.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tm.minilogbook.data.local.GlucoseEntry
import com.tm.minilogbook.domain.model.UnitType
import com.tm.minilogbook.domain.util.formatForDisplay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryItem(entry: GlucoseEntry, unit: UnitType) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${entry.valueMgDl.formatForDisplay()} ${unit.label}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = formatTime(entry.timestamp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun formatTime(timestamp: Long): String {
    val formatter = SimpleDateFormat("dd MMM yy, hh:mm:ss a", Locale.getDefault())
    return formatter.format(Date(timestamp))
}