package com.example.pr2_prm.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pr2_prm.MainActivity
import com.example.pr2_prm.data.Product
import com.example.pr2_prm.databinding.RevisionFragmentBinding
import java.io.File

class SampleFragment : Fragment() {

    private lateinit var binding: RevisionFragmentBinding
    private var product: Product? = null


    companion object {
        fun newInstance(product: Product): SampleFragment {
            val args = Bundle()
            args.putSerializable("product", product)
            val fragment = SampleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RevisionFragmentBinding.inflate(layoutInflater)
        arguments?.let {
            product = it.getSerializable("product") as? Product
        }
        binding.apply {
            headerReview.text = product?.name
            descriptionReview.text = product?.location
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
        binding.editButton.setOnClickListener{
            val fragment = product?.let { it1 -> EditFragment.newInstance(it1) }
            if (fragment != null) {
                (activity as? MainActivity)?.navigateToEdit(fragment)
            }
        }
    }

    private fun loadPhoto(photo: String){

        photo?.let { imagePath ->
            val file = File(imagePath)
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                binding.imageProduct.setImageBitmap(bitmap)
            }
        }
    }

}
