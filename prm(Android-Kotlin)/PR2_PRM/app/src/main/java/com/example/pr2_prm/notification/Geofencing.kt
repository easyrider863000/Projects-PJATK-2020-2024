package com.example.pr2_prm.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.example.pr2_prm.service.AlertService

private const val REQUEST_CODE = 2

object Geofencing {

    private var nameProduct: String = ""

    fun createGeofence(context: Context, latLng: LatLng, productName: String) {
        nameProduct = productName

        val geofence = Geofence.Builder()
            .setCircularRegion(latLng.latitude, latLng.longitude, 50f)
            .setRequestId(nameProduct)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()

        val request = GeofencingRequest.Builder()
            .addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .build()

        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", context.packageName, null)
            ).let {
                context.startActivity(it)
            }
        } else {
            LocationServices.getGeofencingClient(context)
                .addGeofences(request, makePendingNotification(context))
                .addOnFailureListener { println(it) }
                .addOnSuccessListener { println("Geofence added with id " + geofence.requestId) }
        }
    }

    private fun makePendingNotification(context: Context): PendingIntent =
        PendingIntent.getService(
            context, REQUEST_CODE,
            Intent(context, AlertService::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

    fun removeGeofence(context: Context, id: String) {
        val requestIds = listOf(id)
        LocationServices.getGeofencingClient(context)
            .removeGeofences(requestIds)
            .addOnSuccessListener {
                println("Geofence removed with id $id")
            }
            .addOnFailureListener {
                println("Failed to remove geofence with id $id")
            }
    }
}
