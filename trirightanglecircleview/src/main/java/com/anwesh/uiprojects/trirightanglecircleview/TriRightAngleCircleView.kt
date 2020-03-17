package com.anwesh.uiprojects.trirightanglecircleview

/**
 * Created by anweshmishra on 17/03/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 3
val strokeFactor : Float = 90f
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#4CAF50")
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 25
val rFactor : Float = 6f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawRightAngleLine(i : Int, scale : Float, size : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val sci : Float = sf.divideScale(i, lines)
    val r : Float = size / rFactor
    val x : Float = size * sci
    save()
    rotate(-45f * i)
    drawLine(0f, 0f, x, 0f, paint)
    drawCircle(x, 0f, r * sci, paint)
    restore()
}

fun Canvas.drawRightAngleLines(scale : Float, size : Float, paint : Paint) {
    for (j in (0..lines - 1)) {
        drawRightAngleLine(j, scale, size, paint)
    }
}

fun Canvas.drawRALNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = foreColor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    save()
    translate(w / 2, gap * (i + 1))
    drawRightAngleLines(scale, size, paint)
    restore()
}
