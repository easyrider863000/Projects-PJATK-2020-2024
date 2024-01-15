package com.example.pr2_prm.photomaker

import android.graphics.Color

data class PaintText(
    var text: String = "",
    val color: Int = Color.CYAN,
    val size: Float,
    var x: Float = 0f,
    var y: Float = 0f
)
