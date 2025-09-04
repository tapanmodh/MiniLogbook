package com.tm.minilogbook.ui.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.tm.minilogbook.R
import com.tm.minilogbook.domain.model.UnitType

@Composable
fun UnitSelection(
    selectedUnit: UnitType,
    onUnitSelected: (UnitType) -> Unit
) {
    Text(
        text = stringResource(R.string.add_measurement),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )

    Column(
        modifier = Modifier
            .selectableGroup()
            .padding(top = 8.dp)
    ) {
        UnitType.entries.forEach { unit ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = unit == selectedUnit,
                        onClick = { onUnitSelected(unit) },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = unit == selectedUnit,
                    onClick = null
                )
                Text(
                    text = unit.label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}