package com.example.pr2_prm.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pr2_prm.MainActivity
import com.example.pr2_prm.Navigable
import com.example.pr2_prm.ProductsAdapter
import com.example.pr2_prm.R
import com.example.pr2_prm.data.Product
import com.example.pr2_prm.data.ProductDB
import com.example.pr2_prm.data.ProductEntity
import com.example.pr2_prm.databinding.ListFragmentBinding
import com.example.pr2_prm.notification.Geofencing
import java.io.File
import kotlin.concurrent.thread

class ListFragment : Fragment(), ProductsAdapter.ProductClickListener,
    ProductsAdapter.ProductLongClickListener {

    private lateinit var binding: ListFragmentBinding
    private var adapter: ProductsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ListFragmentBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ProductsAdapter(this, this)
        loadData()

        binding.list.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.addButton.setOnClickListener {
            (activity as? Navigable)?.navigate(Navigable.Destination.Add)
        }

        val count = adapter?.itemCount.toString()
    }

    fun loadData() = thread {
        val products = ProductDB.open(requireContext()).products.getAllProducts()
            .map { entity -> Product(
                entity.id,
                entity.name,
                entity.location,
                entity.photo,
                entity.date)
            }
        products.sortedBy { product -> product.id }
        adapter?.replace(products)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }


    override fun onItemClick(position: Int, product: Product) {
        val fragment = SampleFragment.newInstance(product)
        (activity as? MainActivity)?.navigateToreview(fragment)
    }

    override fun onItemLongClick(position: Int, product: Product) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.delete_text_header)
        val askDelete = getString(R.string.delete_text_ask, product.name)
        builder.setMessage(askDelete)
        val productToDelete = ProductEntity(
            id = product.id,
            name = product.name,
            location = product.location,
            date = product.date,
            photo = product.photo
        )
        builder.setPositiveButton(R.string.delete_text_yes) { _, _ ->
            thread {
                Geofencing.removeGeofence(requireContext(), productToDelete.date)
                ProductDB.open(requireContext()).products.deleteProduct(productToDelete)
                val file = File(productToDelete.photo)
                if (file.exists()) {
                    file.delete()
                }
                loadData()
            }
            val count = adapter?.itemCount.toString()
        }
        builder.setNegativeButton(R.string.delete_text_no, null)
        builder.create().show()
    }
}
