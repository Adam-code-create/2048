package uz.gita.a2048.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import uz.gita.a2048.data.MySide
import kotlin.math.abs

class MyTouchListener (private val context: Context) : View.OnTouchListener {
    private val gesture = GestureDetector(context, MyGestureListener())
    private var sideListener :((MySide) -> Unit)? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        return gesture.onTouchEvent(event)
    }

    inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        private val MIN_LENGTH = 100
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }
        override fun onFling(
            oldEvent: MotionEvent,
            newEvent: MotionEvent,
            velocityX: Float,
            velocityY: Float,
        ): Boolean {
            var state = false
            val diffX = oldEvent.x - newEvent.x
            val diffY = oldEvent.y - newEvent.y
            if (abs(diffX)> abs(diffY)){
                if (abs(diffX) > MIN_LENGTH && oldEvent.x > newEvent.x){
                    sideListener?.invoke(MySide.LEFT)
                    state = true
                } else if (abs(diffX) > MIN_LENGTH){
                    sideListener?.invoke(MySide.RIGHT)
                    state = true
                }
            } else {
                if (abs(diffY) > MIN_LENGTH && oldEvent.y > newEvent.y){
                    sideListener?.invoke(MySide.UP)
                    state = true
                } else if (abs(diffY) > MIN_LENGTH){
                    sideListener?.invoke(MySide.DOWN)
                    state = true
                }
            }

            return state
        }


    }

    fun setSideListener (f :(MySide) -> Unit){
        sideListener = f
    }


}