package com.example.pr2_prm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDB : RoomDatabase() {
    abstract val products: ProductDao

    companion object {
        fun open(context: Context): ProductDB = Room.databaseBuilder(
            context, ProductDB::class.java, "products.db"
        ).build()
    }
}

