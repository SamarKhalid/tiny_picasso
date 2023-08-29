package com.example.tinypicasso

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context , attrs : AttributeSet): View(context , attrs) {
    private var drawPath : CustomPath? = null
    private var canvasBitMap : Bitmap? = null
    private var canvasPaint : Paint? = null
    private var drawPaint : Paint? = null
    private var brushSize : Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas : Canvas? = null
    private val mPaths = ArrayList<CustomPath>() // ArrayList for Paths

    init {
        setUpDrawing()
    }

    private fun setUpDrawing() {
        drawPaint = Paint()
        drawPaint!!.color = color
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        drawPath = CustomPath(color , brushSize)
        canvasPaint =Paint(Paint.DITHER_FLAG)
        brushSize = 20.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitMap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas= Canvas(canvasBitMap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitMap!! , 0f,0f,canvasPaint)
        if (!drawPath!!.isEmpty){
            drawPaint!!.strokeWidth = drawPath!!.thickness
            drawPaint!!.color = drawPath!!.color
            canvas.drawPath(drawPath!!, drawPaint!!)
        }

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath?.color = color
                drawPath?.thickness = brushSize
                drawPath?.reset()
                drawPath?.moveTo(
                    touchX,
                    touchY
                )
            }

            MotionEvent.ACTION_MOVE -> {
                drawPath?.lineTo(
                    touchX,
                    touchY
                )
            }

            MotionEvent.ACTION_UP -> {
                mPaths.add(drawPath!!)
                drawPath = CustomPath(color, brushSize)
            }
            else -> return false
        }

        invalidate()
        return true
    }


    internal inner class CustomPath(var color : Int , var thickness: Float) : Path() {

    }

}