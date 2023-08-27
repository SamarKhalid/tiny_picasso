package com.example.tinypicasso

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class DrawingView(context: Context , attrs : AttributeSet): View(context , attrs) {
    private var drawPath : CustomPath? = null
    private var canvasBitMap : Bitmap? = null
    private var canvasPaint : Paint? = null
    private var drawPaint : Paint? = null
    private var brushSize : Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas : Canvas? = null

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


    internal inner class CustomPath(var color : Int , var thickness: Float) : Path() {

    }

}