package com.coding.smkcoding_project_2.util

import android.content.Context
import android.widget.Toast

fun tampilToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}