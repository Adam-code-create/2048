package uz.gita.a2048.domain
import uz.gita.a2048.shared.SharedPref
import java.lang.StringBuilder
import kotlin.random.Random

class MyRepository private constructor() {
    private val pref = SharedPref.getSharedPref()
    companion object {
       private lateinit var instance: MyRepository
        fun getRepository(): MyRepository {
            if (!::instance.isInitialized) {
                instance = MyRepository()
            }
            return instance
        }
    }
    private var ADD_AMOUNT = 2
    var startState  = pref.started
    var score = 0
    var movement = 0

    val array =
        arrayOf(arrayOf(0, 0, 0, 0), arrayOf(0, 0, 0, 0), arrayOf(0, 0, 0, 0), arrayOf(0, 0, 0, 0))

    init {
        start()
    }


    private fun start() {
        if (pref.position == 2){
            loadData()
            return
        }
        val list = ArrayList<Pair<Int, Int>>()
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (array[i][j] == 0) {
                    list.add(Pair(i, j))
                }

            }
        }
        val index = Random.nextInt(0, list.size)
        var index2 = Random.nextInt(0, list.size)
        while (index == index2) {
            index2 = Random.nextInt(0, list.size)
        }
        array[list[index].first][list[index].second] = ADD_AMOUNT
        array[list[index2].first][list[index2].second] = ADD_AMOUNT


    }

    private fun addNewAmount() {
        val list = ArrayList<Pair<Int, Int>>()
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                if (array[i][j] == 0) {
                    list.add(Pair(i, j))
                }
            }
        }
        if (list.size == 0){
            if (check() || check2()){
                return
            }else pref.isFinish = true
        }else{
            val index = Random.nextInt(0, list.size)
            array[list[index].first][list[index].second] = ADD_AMOUNT

        }

    }

    private fun check() : Boolean{
        for (i in 0 until 4){
            for (j in 0 until 3){
                if (array[i][j] == array[i][j+1]){
                    return true
                }
            }
        }

        return false
    }
    private fun check2() : Boolean{
        for (i in 0 until 4){
            for (j in 0 until 3){
                if (array[j][i] == array[j+1][i]){
                    return true
                }
            }
        }
        return false
    }

    private fun loadData() {
        score = pref.score
        movement =pref.movement
        val text = pref.number!!
        val list = text.split("###")
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                when (i) {
                    0 -> array[i][j] = list[j].toInt()
                    1 -> array[i][j] = list[j + 4].toInt()
                    2 -> array[i][j] = list[j + 8].toInt()
                    3 -> array[i][j] = list[j + 12].toInt()
                }

            }

        }
    }

    fun reload() {
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                array[i][j] = 0
            }
        }
        score = pref.score
        movement =pref.movement
        start()
    }

    fun startGameByTarget(){
        if (pref.position == 2){
            loadData()
            return
        } else {
            reload()
            pref.started = false
        }
    }

    fun swipeLeft() {
       val target = pref.target!!.toInt()
        val builder = StringBuilder()
        var finish = false
        for (i in array.indices) {
            val list = ArrayList<Int>()
            for (j in 0 until 4) {
                if (array[i][j] != 0)
                    list.add(array[i][j])
            }
            var index = 0
            while (index < list.size - 1) {
                if (list[index] == list[index + 1]) {
                    list[index] *= 2
                    score += list[index]
                    list.removeAt(index + 1)
                }
                index++
            }

            for (j in 0 until 4) {
                array[i][j] = if (j < list.size) list[j]
                else 0
            }
            for (j in 0 until 4){
                if (array[i][j] == target) {
                    finish = true
                    pref.isWin = true
                }
            }
        }
        if (!finish) {
            movement++
            addNewAmount()
        }
        for (i in 0 until 4){
            for(j in 0 until 4){
                builder.append("${array[i][j]}###")
                pref.number = builder.toString()
            }
        }

    }

    fun swipeRight() {
        val target = pref.target!!.toInt()
        var finish = false
        val builder = StringBuilder()
        for (i in array.indices) {
            val list = ArrayList<Int>()
            for (j in 0 until 4) {
                if (array[i][j] != 0) {
                    list.add(array[i][j])
                }
            }

            var index = list.size - 1
            while (index > 0) {
                if (list[index] == list[index - 1]) {
                    list[index] *= 2
                    score += list[index]
                    list.removeAt(index - 1)

                }
                index--
            }
            for (j in 0 until 4) {
                if (j < 4 - list.size) array[i][j] = 0
                else array[i][j] = list[j - (4 - list.size)]
            }
            for (j in 0 until 4){

                if (array[i][j] == target) {
                    finish = true
                    pref.isWin = true
                }

            }
        }
        if (!finish) {
            movement++
            addNewAmount()
        }
        for (i in 0 until 4){
            for(j in 0 until 4){
                builder.append("${array[i][j]}###")
                pref.number = builder.toString()
            }
        }
    }

    fun swipeUp() {
        val target = pref.target!!.toInt()
        val builder = StringBuilder()
        var finish = false
        for (i in array.indices) {
            val list = ArrayList<Int>()
            for (j in array.indices) {
                if (array[j][i] != 0) list.add(array[j][i])
            }
            var index = 0
            while (index < list.size - 1) {
                if (list[index] == list[index + 1]) {
                    list[index] *= 2
                    score += list[index]
                    list.removeAt(index + 1)
                }
                index++
            }
            for (j in 0 until 4) {
                array[j][i] = if (j < list.size) list[j]
                else 0
            }
            for (j in 0 until 4){
                if (array[i][j] == target) {
                    finish = true
                    pref.isWin = true
                }
            }
        }
        if (!finish) {
            movement++
            addNewAmount()
        }

        for (i in 0 until 4){
            for(j in 0 until 4){
                builder.append("${array[i][j]}###")
                pref.number = builder.toString()
            }
        }
    }

    fun swipeDown(){
        val target = pref.target!!.toInt()
        val builder = StringBuilder()
        var finish = false
        for (i in array.indices){
            val list = ArrayList<Int>()
            for (j in array.indices){
                if (array[j][i] != 0) list.add(array[j][i])
            }

            var index = list.size -1
            while (index > 0){
                if (list[index] == list[index - 1]){
                    list[index] *= 2
                    score += list[index]
                    list.removeAt(index-1)
                }
                index--
            }

            for (j in 0 until 4){
                if (j < 4- list.size) array[j][i] = 0
                else array[j][i] = list[j - (4-list.size)]
            }
            for (j in 0 until 4){
                if (array[i][j] == target) {
                    finish = true
                    pref.isWin = true

                }
            }
        }
        if (!finish) {
            movement++
            addNewAmount()
        }
        for (i in 0 until 4){
            for(j in 0 until 4){
                builder.append("${array[i][j]}###")
                pref.number = builder.toString()
            }
        }
    }




}

