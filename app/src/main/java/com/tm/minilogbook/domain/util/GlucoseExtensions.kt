package com.tm.minilogbook.domain.util

import com.tm.minilogbook.domain.model.UnitType
import java.text.NumberFormat
import java.util.Locale

object GlucoseConstants {
    const val MG_DL_PER_MMOL_L = 18.0182
}

fun Double.toMgDl(unit: UnitType): Double = when (unit) {
    UnitType.MG_DL -> this
    UnitType.MMOL_L -> this * GlucoseConstants.MG_DL_PER_MMOL_L
}

fun Double.fromMgDl(toUnit: UnitType): Double = when (toUnit) {
    UnitType.MG_DL -> this
    UnitType.MMOL_L -> this / GlucoseConstants.MG_DL_PER_MMOL_L
}

fun Double.formatForDisplay(locale: Locale = Locale.getDefault()): String {
    return NumberFormat.getNumberInstance(locale)
        .apply { maximumFractionDigits = 2 }.format(this)
}