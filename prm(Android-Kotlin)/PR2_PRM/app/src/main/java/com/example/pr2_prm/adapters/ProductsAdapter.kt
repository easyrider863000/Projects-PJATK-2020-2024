package com.example.pr2_prm

import android.graphics.BitmapFactory
import android.os.Looper
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pr2_prm.data.Product
import com.example.pr2_prm.databinding.ListItemBinding

class ProductsAdapter(private val productClickListener: ProductClickListener, private val productLongClickListener: ProductLongClickListener) : RecyclerView.Adapter<ProductViewHolder>() {
    private val data = mutableListOf<Product>()

    private val handler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding).also { viewHolder ->
            binding.root.setOnClickListener {
                val position = viewHolder.layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = data[position]
                    productClickListener.onItemClick(position, product)
                }
            }
            binding.root.setOnLongClickListener {
                val position = viewHolder.layoutPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = data[position]
                    productLongClickListener.onItemLongClick(position, product)
                }
                true
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun replace(newData: List<Product>) {
        data.clear()
        data.addAll(newData)
        handler.post {
            notifyDataSetChanged()
        }
    }

    interface ProductClickListener {
        fun onItemClick(position: Int, product: Product)
    }

    interface ProductLongClickListener {
        fun onItemLongClick(position: Int, product: Product)
    }

}

class ProductViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Product) {
        binding.header.text = if (product.name.length > 10) {
            product.name.substring(0, 10).plus("..")
        } else {
            product.name
        }
        binding.location.text = if (product.location.length > 20) {
            product.location.substring(0, 10).plus("..")
        } else {
            product.location
        }

        loadPhoto(product.photo)
    }

    private fun loadPhoto(photo: String) {
        photo?.let { imagePath ->
            val bitmap = BitmapFactory.decodeFile(imagePath)
            binding.image.setImageBitmap(bitmap)
        }
    }


}
