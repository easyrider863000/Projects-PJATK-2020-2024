package com.example.pr2_prm.fragments

import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import com.example.pr2_prm.Navigable
import com.example.pr2_prm.R
import com.example.pr2_prm.data.Product
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

class EditFragment: Fragment(), OnLocationSelectedListener, OnPhotoTakenListener {
    private lateinit var binding: FragmentAddBinding
    private var product: Product? = null
    private var photo: String? = null
    private var location: LatLng? = null

    companion object {
        fun newInstance(product: Product): EditFragment {
            val args = Bundle()
            args.putSerializable("product", product)
            val fragment = EditFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddBinding.inflate(layoutInflater)
        arguments?.let {
            product = it.getSerializable("product") as? Product
        }
        binding.apply {
            headerNew.text = Editable.Factory.getInstance().newEditable(product?.name ?: "")
            locationNew.text = Editable.Factory.getInstance().newEditable(product?.location ?: "")
        }
        product?.let { loadPhoto(it.photo) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.locationNew.setOnClickListener{
            navigateToMaps()
        }
        binding.imageView.setOnClickListener{
            navigateToPaint()
        }

        photo?.let { loadPhoto(it) }

        if (location != null){
            val address = location?.let { getCityAndAddressFromLatLng(it) }
            binding.locationNew.setText(address)
        }

        binding.save.setOnClickListener {
            if (binding.headerNew.text.toString() != "" && binding.locationNew.text.toString() != "") {
                var updatedProduct: ProductEntity? = null

                if (!photo.equals(product?.photo) && photo != null) {
                    product?.let { it1 -> changePhoto(it1.photo) }
                    updatedProduct = photo?.let { it1 ->
                        ProductEntity(
                            id = product!!.id,
                            name = binding.headerNew.text.toString(),
                            location = binding.locationNew.text.toString(),
                            photo = it1,
                            date = Date().toLocaleString()
                        )
                    }
                } else {
                    updatedProduct = ProductEntity(
                        id = product!!.id,
                        name = binding.headerNew.text.toString(),
                        location = binding.locationNew.text.toString(),
                        photo = product!!.photo,
                        date = Date().toLocaleString()
                    )
                }

                if (!updatedProduct?.location.equals(product?.location)){
                    product?.let { it1 -> updatedProduct?.let { it2 -> changeGeo(it1.date, it2.date) } }
                }
                thread {
                    updatedProduct?.let { it1 ->
                        ProductDB.open(requireContext()).products.updateProduct(
                            it1
                        )
                    }
                }
                (activity as? Navigable)?.navigate(Navigable.Destination.List)
            }
        }
    }

    private fun navigateToMaps() {
            val mapsFragment = MapsFragment()
            mapsFragment.setTargetFragment(this)
            parentFragmentManager.beginTransaction()
                .replace(R.id.maincontainer, mapsFragment)
                .addToBackStack(null)
                .commit()
        }

    private fun loadPhoto(photo: String){
        photo.let { imagePath ->
            val file = File(imagePath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.imageView.setImageBitmap(bitmap)
            }
        }
    }

    override fun onLocationSelected(latLng: LatLng) {
        this.location = latLng
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
        parentFragmentManager.beginTransaction()
            .replace(R.id.maincontainer, paintFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onPhotoTaken(photoPath: String) {
        this.photo = photoPath
    }

    fun changeGeo(oldId: String, newId: String){
        Geofencing.removeGeofence(requireContext(), oldId)
        location?.let { Geofencing.createGeofence(requireContext(), it, newId) }
    }

    fun changePhoto(id: String){
        val file = File(id)
        if (file.exists()) {
            file.delete()
        }
    }

}