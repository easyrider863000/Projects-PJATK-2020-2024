package com.example.pr2_prm.localisation

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar
import com.example.pr2_prm.R

class MapsFragment : Fragment() {

    private lateinit var map: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        turnOnLocation()
        googleMap.setOnMapClickListener(::onMapClick)
    }
    private var targetFragment: OnLocationSelectedListener? = null

    private fun onMapClick(latLng: LatLng) {
        drawPoint(latLng)
        askForSave(latLng)
    }

    private fun askForSave(latLng: LatLng) {
        Snackbar.make(
            requireView().findViewById(R.id.root),
            "Save location?",
            Snackbar.LENGTH_INDEFINITE
        ).setAction("save") {
            returnToPreviousFragment(latLng)
        }.show()
    }

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @SuppressLint("MissingPermission")
    private val onPermissionResult = { results: Map<String, Boolean> ->
        if (ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        {
            map.isMyLocationEnabled = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            onPermissionResult
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun turnOnLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        {
            map.isMyLocationEnabled = true
        } else {
            permissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }
    }

    private fun drawPoint(latLng: LatLng) {
        val circle = CircleOptions()
            .strokeColor(Color.RED)
            .radius(30.0)
            .center(latLng)
            .strokeWidth(5f)
        map.apply {
            clear()
            addCircle(circle)
        }
    }

    private fun returnToPreviousFragment(latLng: LatLng) {
        targetFragment?.onLocationSelected(latLng)
        parentFragmentManager.popBackStack()
    }


    fun setTargetFragment(listener: OnLocationSelectedListener) {
        targetFragment = listener
    }
}
interface OnLocationSelectedListener {
    fun onLocationSelected(latLng: LatLng)
}

