package com.tm.minilogbook.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tm.minilogbook.domain.model.UnitType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserPreferencesKeys {
    val UNIT_TYPE = stringPreferencesKey("unit_type")
}

class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val unitTypeFlow: Flow<UnitType> =
        context.dataStore.data.map { prefs ->
            when (prefs[UserPreferencesKeys.UNIT_TYPE]) {
                UnitType.MMOL_L.name -> UnitType.MMOL_L
                else -> UnitType.MG_DL
            }
        }

    suspend fun setUnitType(unit: UnitType) {
        context.dataStore.edit { prefs ->
            prefs[UserPreferencesKeys.UNIT_TYPE] = unit.name
        }
    }
}