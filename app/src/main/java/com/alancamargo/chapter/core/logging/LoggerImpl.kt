package com.alancamargo.chapter.core.logging

import android.util.Log
import javax.inject.Inject

private const val TAG = "LOG_ALAN"

class LoggerImpl @Inject constructor() : Logger {

    override fun error(message: String, throwable: Throwable) {
        Log.e(TAG, message, throwable)
    }
}
