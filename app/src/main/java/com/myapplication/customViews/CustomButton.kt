package com.myapplication.customViews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class CustomButton @JvmOverloads constructor(
        context: Context,
        attrSet: AttributeSet? = null,
        defStyleResource: Int = 0
) : AppCompatButton(context, attrSet, defStyleResource) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val progressRect = Rect()
        progressRect.set(
                0,0,200,100
        )
        val paintF = Paint()
        paintF.color = Color.parseColor("#458456")
        var rectF = RectF(progressRect)

        canvas?.drawRoundRect(rectF, 20.0f,20.0f , paintF)

        paintF.color = Color.parseColor("#999999")
        canvas?.drawCircle(50.0f, 50.0f,50.0f , paintF)
    }
}