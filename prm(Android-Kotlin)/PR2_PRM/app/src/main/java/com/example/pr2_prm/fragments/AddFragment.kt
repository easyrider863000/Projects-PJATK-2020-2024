package com.example.pr2_prm.fragments

import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.model.LatLng
import com.example.pr2_prm.Navigable
import com.example.pr2_prm.R
import com.example.pr2_prm.data.ProductDB
import com.example.pr2_prm.data.ProductEntity
import com.example.pr2_prm.databinding.FragmentAddBinding
import com.example.pr2_prm.localisation.MapsFragment
import com.example.pr2_prm.localisation.OnLocationSelectedListener
import com.example.pr2_prm.notification.Geofencing
import com.example.pr2_prm.photomaker.OnPhotoTakenListener
import com.example.pr2_prm.photomaker.PaintFragment
import java.io.File
import java.util.Date
import java.util.Locale
import kotlin.concurrent.thread

class AddFragment : Fragment(), OnLocationSelectedListener, OnPhotoTakenListener {

    private lateinit var binding: FragmentAddBinding
    private var photo: String? = null
    private var location: LatLng? = null

    companion object {
        private const val PAINT_FRAGMENT_REQUEST_CODE = 1
        private const val MAPS_FRAGMENT_REQUEST_CODE = 2
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val address = location?.let { getCityAndAddressFromLatLng(it) }
        loadPhoto()

        binding.imageView.setOnClickListener {
            navigateToPaint()
        }

        if (location != null){
            binding.locationNew.setText(address)
            println(binding.locationNew.text)
        }

        binding.save.setOnClickListener {
            if (binding.headerNew.text.toString().isNotBlank() && !binding.locationNew.text.toString().equals("Select new location") && photo != null) {
                val newProduct = address?.let { it1 ->
                    ProductEntity(
                        name = binding.headerNew.text.toString(),
                        location = it1,
                        date = Date().toLocaleString(),
                        photo = photo ?: ""
                    )
                }
                thread {
                    newProduct?.let { it1 ->
                        ProductDB.open(requireContext()).products.insertProduct(
                            it1
                        )
                    }
                    location?.let { it1 -> newProduct?.let { it2 -> Geofencing.createGeofence(requireContext(), it1, it2.date) } }
                    (activity as? Navigable)?.navigate(Navigable.Destination.List)
                }
            }
        }

        binding.locationNew.setOnClickListener {
            navigateToMaps()
        }
    }

    private fun getCityAndAddressFromLatLng(latLng: LatLng): String? {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val addresses: List<Address>?

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        if (addresses.isNullOrEmpty()) {
            return null
        }

        val address: Address = addresses[0]

        val city = address.locality
        val fullAddress = address.getAddressLine(0)

        val sb = StringBuilder()

        if (city != null) {
            sb.append(city)
        }

        if (fullAddress != null) {
            if (sb.isNotEmpty()) {
                sb.append(", ")
            }
            sb.append(fullAddress)
        }
        return sb.toString()
    }

    private fun navigateToPaint() {
        val paintFragment = PaintFragment()
        paintFragment.setTargetFragment(this)
        paintFragment.setTargetFragment(this, PAINT_FRAGMENT_REQUEST_CODE)
        parentFragmentManager.beginTransaction()
            .replace(R.id.maincontainer, paintFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onPhotoTaken(photoPath: String) {
        this.photo = photoPath
    }

    private fun navigateToMaps() {
        val mapsFragment = MapsFragment()
        mapsFragment.setTargetFragment(this)
        mapsFragment.setTargetFragment(this, MAPS_FRAGMENT_REQUEST_CODE)
        parentFragmentManager.beginTransaction()
            .replace(R.id.maincontainer, mapsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onLocationSelected(latLng: LatLng) {
        this.location = latLng
    }

    private fun loadPhoto() {
        photo?.let { imagePath ->
            val file = File(imagePath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.imageView.setImageBitmap(bitmap)
            }
        }
    }

}
