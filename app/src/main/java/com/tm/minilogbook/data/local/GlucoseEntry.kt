package com.tm.minilogbook.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "glucose_entries")
data class GlucoseEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val valueMgDl: Double, // always store in mg/dL
    val timestamp: Long = System.currentTimeMillis()
)