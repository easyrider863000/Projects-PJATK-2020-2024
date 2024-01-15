package com.example.pr2_prm.data

import java.io.Serializable


data class Product(
    val id: Long,
    val name: String,
    val location: String,
    val photo: String,
    val date: String
) : Serializable