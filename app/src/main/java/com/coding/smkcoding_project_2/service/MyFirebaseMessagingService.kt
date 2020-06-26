package com.coding.smkcoding_project_2.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d("TAG", "the token refreshed: $token")
    }
}