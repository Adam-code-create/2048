package uz.gita.a2048.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, duration : Int= Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(),message, duration).show()
}

fun List<Int>.show() : String {
    val sb = StringBuilder()
    for (i in this.indices)
        sb.append("${this[i]}" ,)
    return sb.toString()
}