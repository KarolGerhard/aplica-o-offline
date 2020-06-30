package com.example.poctnoffline

import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController


var LAST_NAV_TIME = 0L
private const val MIN_NAV_INTERVAL = 600

fun Fragment.navigate(action: NavDirections, navOptions: NavOptions? = null) {
    if (SystemClock.elapsedRealtime() - LAST_NAV_TIME < MIN_NAV_INTERVAL) {
        return
    }
    LAST_NAV_TIME = SystemClock.elapsedRealtime()
    findNavController().navigate(action, navOptions)
}