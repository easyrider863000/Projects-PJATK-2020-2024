package com.example.pr2_prm.photomaker

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout

class PaintView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var color: Int = Color.CYAN
    var size: Float = 150f
    var background: Bitmap? = null
        set(value) {
            field = value
            invalidate()
        }
    private val texts = mutableListOf<PaintText>()
    private val defaultPaint = Paint()
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.LEFT
    }
    private var editText: EditText? = null

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawText(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        background?.let {
            val rect = Rect(0, 0, width, height)
            canvas.drawBitmap(it, null, rect, defaultPaint)
        }
    }

    private fun drawText(canvas: Canvas) {
        texts.forEach { textWithSettings ->
            paint.apply {
                textSize = textWithSettings.size
                color = textWithSettings.color
            }
            canvas.drawText(textWithSettings.text, textWithSettings.x, textWithSettings.y, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        event ?: return true
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                showEditText(event.x, event.y)
                return true
            }
        }
        return false
    }

    private fun showEditText(x: Float, y: Float) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)

        var layoutParams =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.leftMargin = x.toInt()
        layoutParams.topMargin = y.toInt()

        editText = EditText(context)
        editText?.apply {
            layoutParams = layoutParams
            setBackgroundColor(Color.TRANSPARENT)
            setTextColor(color)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
            setSingleLine()
            setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                    addText(text.toString(), x, y)
                    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
                    removeEditText()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }

        val rootView = rootView as? ViewGroup
        rootView?.addView(editText)
        editText?.requestFocus()
    }

    private fun removeEditText() {
        val rootView = rootView as? ViewGroup
        rootView?.removeView(editText)
        editText = null
    }

    private fun addText(text: String, x: Float, y: Float) {
        val paintText = PaintText(
            text = text,
            color = color,
            size = size,
            x = x,
            y = y
        )
        texts.add(paintText)
        invalidate()
    }

    fun generateBitMap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        drawBackground(canvas)
        drawText(canvas)

        return bitmap
    }
}
