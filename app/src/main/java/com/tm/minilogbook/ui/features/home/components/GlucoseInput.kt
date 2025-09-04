package com.tm.minilogbook.ui.features.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tm.minilogbook.R
import com.tm.minilogbook.domain.model.UnitType
import com.tm.minilogbook.domain.util.formatForDisplay
import com.tm.minilogbook.domain.util.fromMgDl
import com.tm.minilogbook.domain.util.toMgDl

@Composable
fun GlucoseInput(
    selectedUnit: UnitType,
    onSave: (Double) -> Unit
) {
    var rawValueMgDl by rememberSaveable { mutableStateOf<Double?>(null) }
    var input by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(selectedUnit, rawValueMgDl) {
        rawValueMgDl?.let {
            input = it.fromMgDl(selectedUnit).formatForDisplay()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { newValue ->
                    if (newValue.isEmpty() ||
                        (newValue.all { it.isDigit() || it == '.' } &&
                        newValue.count { it == '.' } <= 1)
                    ) {
                        input = newValue
                        rawValueMgDl = newValue.toDoubleOrNull()?.toMgDl(selectedUnit)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
            )

            Text(
                text = selectedUnit.label,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                rawValueMgDl?.let(onSave)
                input = ""
                rawValueMgDl = null
            },
            enabled = input.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.save),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}