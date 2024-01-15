package com.example.pr2_prm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Query("SELECT * FROM product;")
    fun getAllProducts(): List<ProductEntity>

    @Update
    fun updateProduct(product: ProductEntity)

    @Insert
    fun insertProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(product: ProductEntity)
}
