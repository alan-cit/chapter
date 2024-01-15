package com.alancamargo.chapter.core.tools

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ToastHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ToastHelper {

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
