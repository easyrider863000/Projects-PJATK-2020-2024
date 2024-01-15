package com.example.pr2_prm.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.pr2_prm.notification.Notifications
import com.google.android.gms.location.GeofencingEvent

private const val NOTIFICATION_ID = 1

class AlertService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val geofence = intent?.let { GeofencingEvent.fromIntent(it) }
        val id = geofence?.triggeringGeofences?.firstOrNull()?.requestId
        id?.let { showNotification() }
        stopForeground(true)
        stopSelf()
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    private fun showNotification() {
        val notification = Notifications.createNotification(this)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}

