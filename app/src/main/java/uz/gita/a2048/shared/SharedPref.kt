package uz.gita.a2048.shared

import android.content.Context
import uz.gita.a2048.app.App

class SharedPref private constructor(){
    private val pref = App.instance.getSharedPreferences("2048", Context.MODE_PRIVATE)

    companion object{
        private lateinit var pref : SharedPref

        fun getSharedPref () : SharedPref{
            if (!::pref.isInitialized){
                pref = SharedPref()
            }
            return pref
        }
    }

    var score : Int
    get() = pref.getInt("score", 0)
    set(value) = pref.edit().putInt("score", value).apply()

    var movement : Int
        get() = pref.getInt("move", 0)
        set(value) = pref.edit().putInt("move", value).apply()

    var number : String?
        get() = pref.getString("number", "")
        set(value) = pref.edit().putString("number", value).apply()

    var started :Boolean
        get() = pref.getBoolean("started", true)
        set(value) = pref.edit().putBoolean("started", value).apply()


    var isFinish :Boolean
        get() = pref.getBoolean("isFinish", false)
        set(value) = pref.edit().putBoolean("isFinish", value).apply()

    var isWin :Boolean
        get() = pref.getBoolean("isWin", false)
        set(value) = pref.edit().putBoolean("isWin", value).apply()

    var target : String?
        get() = pref.getString("target", "128")
        set(value) = pref.edit().putString("target", value).apply()


    var position : Int
        get() = pref.getInt("position", 1)
        set(value) = pref.edit().putInt("position", value).apply()

    var top128 : Int
        get() = pref.getInt("top128", 0)
        set(value) = pref.edit().putInt("top128", value).apply()

    var top256 : Int
        get() = pref.getInt("top256", 0)
        set(value) = pref.edit().putInt("top256", value).apply()

    var top512 : Int
        get() = pref.getInt("top512", 0)
        set(value) = pref.edit().putInt("top512", value).apply()

    var top1024 : Int
        get() = pref.getInt("top1024", 0)
        set(value) = pref.edit().putInt("top1024", value).apply()

    var top2048 : Int
        get() = pref.getInt("top2048", 0)
        set(value) = pref.edit().putInt("top2048", value).apply()

    var top4096 : Int
        get() = pref.getInt("top4096", 0)
        set(value) = pref.edit().putInt("top4096", value).apply()

    var top8192 : Int
        get() = pref.getInt("top8192", 0)
        set(value) = pref.edit().putInt("top8192", value).apply()

}