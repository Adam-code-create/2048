package uz.gita.a2048.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.a2048.R
import uz.gita.a2048.data.MySide
import uz.gita.a2048.databinding.ScreenMainBinding
import uz.gita.a2048.shared.SharedPref
import uz.gita.a2048.utils.MyTouchListener
import uz.gita.a2048.utils.backgroundColor
import uz.gita.a2048.viewmodels.MainViewModel

class MainScreen :Fragment (R.layout.screen_main) {
    private var _viewBinding : ScreenMainBinding? = null
    private val vb get()  = _viewBinding!!
    private val viewModel by viewModels<MainViewModel>()
    private val views = ArrayList<TextView>(16)
    private val pref = SharedPref.getSharedPref()

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = ScreenMainBinding.bind(view)
        loadViews()
        var isFinished: Boolean
        var isWin : Boolean
        if (!pref.isFinish && !pref.isWin){
            vb.restart.isClickable=true
            vb.back.isClickable=true
        }


        vb.titleTarget.text = pref.target
        vb.score.text =pref.score.toString()
        vb.swipe.text = viewModel.movement().toString()
        when(pref.target!!.toInt()){
            128 -> {
                vb.bestScore.text = pref.top128.toString()
            }
            256 -> {
                vb.bestScore.text = pref.top256.toString()
            }
            512 -> {
                vb.bestScore.text = pref.top512.toString()
            }
            1024 -> {
                vb.bestScore.text = pref.top1024.toString()
            }
            2048 -> {
                vb.bestScore.text = pref.top2048.toString()
            }
            4096 -> {
                vb.bestScore.text = pref.top4096.toString()
            }
            8192 -> {
                vb.bestScore.text = pref.top8192.toString()
            }
        }
        val touchListener = MyTouchListener(requireContext())
        touchListener.setSideListener {
            if (!pref.isFinish && !pref.isWin) {
                pref.position = 2

                when(it){

                    MySide.LEFT -> {
                        viewModel.swipeLeft()
                    }
                    MySide.RIGHT -> {
                        viewModel.swipeRight()
                    }
                    MySide.UP -> {
                        viewModel.swipeUp()
                    }
                    MySide.DOWN -> {
                        viewModel.swipeDown()
                    }
                }
            }

            vb.score.text = viewModel.score().toString()
            vb.swipe.text = viewModel.movement().toString()
            isFinished = pref.isFinish
            if (isFinished){
                pref.position = 1
                vb.restart.isClickable=false
                vb.back.isClickable=false
                vb.mainBg.visibility = View.VISIBLE
                vb.gameOver.visibility =View.VISIBLE
                vb.btnStartAgain.visibility = View.VISIBLE

                vb.btnStartAgain.setOnClickListener {
                    pref.isFinish = false
                    vb.restart.isClickable=true
                    vb.back.isClickable=true
                 //   pref.position = 1
                    vb.mainBg.visibility = View.GONE
                    vb.gameOver.visibility =View.GONE
                    vb.btnStartAgain.visibility = View.GONE
                    pref.movement = 0
                    vb.swipe.text = "0"
                    pref.score = 0
                    vb.score.text = "0"
                    viewModel.reload()

                }
            }
            isWin = pref.isWin
            if (isWin){
                pref.position = 1
                vb.restart.isClickable=false
                vb.back.isClickable=false
                vb.mainBg.visibility = View.VISIBLE
                vb.finalResult.visibility = View.VISIBLE
                vb.youWin.visibility = View.VISIBLE
                vb.finishGameTitle.visibility = View.VISIBLE
                vb.close.visibility = View.VISIBLE
                vb.yourScore.text = pref.score.toString()
                vb.yourSwipe.text = pref.movement.toString()
                vb.finishGameTitle.text = pref.target


                when(pref.target!!.toInt()){
                    128 -> {
                        if (pref.top128 < pref.score) {
                            vb.bestScore.text = pref.score.toString()
                            pref.top128 = pref.score
                        }
                    }
                    256 -> {
                        if (pref.top256 < pref.score) {
                            vb.bestScore.text = pref.score.toString()
                            pref.top256 = pref.score
                        }
                    }
                    512 -> {
                        if (pref.top512 < pref.score) {
                            vb.bestScore.text = pref.score.toString()
                            pref.top512 = pref.score
                        }
                    }
                    1024 -> {
                        if (pref.top1024 < pref.score) {
                            vb.bestScore.text = pref.score.toString()
                            pref.top1024 = pref.score
                        }
                    }
                    2048 -> {
                        if (pref.top2048 < pref.score) {
                            vb.bestScore.text = pref.score.toString()
                            pref.top2048 = pref.score
                        }
                    }
                    4096 -> {
                        if (pref.top4096 < pref.score){
                            vb.bestScore.text = pref.score.toString()
                            pref.top4096 = pref.score
                        }
                    }
                    8192 -> {
                        if (pref.top8192 < pref.score) {
                            vb.bestScore.text = pref.score.toString()
                            pref.top8192 = pref.score
                        }
                    }
                }
                vb.close.setOnClickListener {
                    vb.restart.isClickable=true
                    vb.back.isClickable=true
                    pref.isWin = false
                    vb.mainBg.visibility = View.GONE
                    vb.finalResult.visibility = View.GONE
                    vb.youWin.visibility = View.GONE
                    vb.finishGameTitle.visibility = View.GONE
                    vb.close.visibility = View.GONE
                    pref.movement = 0
                    vb.swipe.text = "0"
                    pref.score = 0
                    vb.score.text = "0"
                    viewModel.reload()
                    findNavController().popBackStack()
                }


            }

        }

        vb.group.setOnTouchListener(touchListener)
        vb.restart.setOnClickListener {
            pref.movement = 0
            vb.swipe.text = "0"
            pref.score = 0
            pref.position = 1
            vb.score.text = "0"
            viewModel.reload()}
        vb.back.setOnClickListener {
                pref.position = 2


            findNavController().popBackStack()
        }
        viewModel.arrayLiveData.observe(viewLifecycleOwner, arrayObserver)
        viewModel.arrayLiveDataScore.observe(viewLifecycleOwner, scoreObserver)
        viewModel.arrayLiveDataMovement.observe(viewLifecycleOwner, movementObserver)
    }

    private fun loadViews (){

        for (i in 0 until vb.group.childCount){
            val l = vb.group[i] as LinearLayoutCompat

            for (j in 0 until l.childCount) {
                views.add(l[j] as TextView)
            }
        }
    }
    private val arrayObserver = Observer<Array<Array<Int>>>{
        for (i in it.indices){
            for (j in it[i].indices){
                views[i * 4 +j].apply {
                    text = if (it[i][j] == 0) ""
                    else "${it[i][j]}"
                    setBackgroundResource(backgroundColor(it[i][j]))
                    }
                }
            }

    }
    private val scoreObserver = Observer<Int>{}
    private val movementObserver = Observer<Int>{}



//    override fun onResume() {
//        super.onResume()
//        vb.time.base = pref.time
//        vb.time.base
//        vb.time.start()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        vb.time.stop()
//        pref.time = vb.time.base
//    }



    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null

    }

}