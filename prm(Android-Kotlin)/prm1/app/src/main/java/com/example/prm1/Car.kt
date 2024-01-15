package com.example.prm1

import androidx.annotation.DrawableRes

data class Car(
    val name: String,
    @DrawableRes
    val resId: Int
)
