package com.tm.minilogbook.data.repository

import com.tm.minilogbook.data.local.GlucoseDao
import com.tm.minilogbook.data.local.GlucoseEntry
import com.tm.minilogbook.data.prefs.UserPreferences
import com.tm.minilogbook.domain.model.UnitType
import com.tm.minilogbook.domain.util.fromMgDl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlucoseRepository @Inject constructor(
    private val dao: GlucoseDao,
    private val preferences: UserPreferences
) {

    suspend fun addGlucose(valueMgDl: Double) {
        dao.insertGlucose(GlucoseEntry(valueMgDl = valueMgDl))
    }

    private val averageFlow: Flow<Double?> = dao.getAllValues().map { entries ->
        if (entries.isEmpty()) null else entries.map { it.valueMgDl }.average()
    }

    val averageDisplayFlow: Flow<Pair<Double?, UnitType>> =
        combine(averageFlow, preferences.unitTypeFlow) { avgMgDl, unitType ->

            val converted = avgMgDl?.fromMgDl(unitType)
            converted to unitType
        }

    val unitTypeFlow = preferences.unitTypeFlow

    val allEntriesFlow: Flow<List<GlucoseEntry>> =
        combine(dao.getAllValues(), preferences.unitTypeFlow) { list, unit ->
            list.map { entry ->
                entry.copy(valueMgDl = entry.valueMgDl.fromMgDl(unit))
            }
        }

    suspend fun setUnitType(type: UnitType) = preferences.setUnitType(type)
}