package uz.gita.a2048.screens

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.gita.a2048.R
import uz.gita.a2048.databinding.ScreenTargetBinding
import uz.gita.a2048.shared.SharedPref
import uz.gita.a2048.viewmodels.TargetViewModel


class TargetScreen : Fragment(R.layout.screen_target) {
    private var _viewBinding : ScreenTargetBinding? = null
    private val vb get()  = _viewBinding!!
    private val viewModel by viewModels<TargetViewModel>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = ScreenTargetBinding.bind(view)
        val pref = SharedPref.getSharedPref()
        val position = pref.position

        if (position == 1){
            vb.btnContinue.visibility = View.GONE

        } else {
            vb.btnContinue.visibility = View.VISIBLE
        }

        val target = pref.target!!.toInt()
        var numbers = arrayOf("128", "256", "512", "1024", "2048", "4096", "8192")
        when(target){
            256 -> numbers = arrayOf("256", "512", "1024", "2048", "4096", "8192", "128")
            512 -> numbers = arrayOf("512", "1024", "2048", "4096", "8192", "128", "256")
            1024 -> numbers = arrayOf("1024", "2048", "4096", "8192", "128", "256", "512")
            2048 -> numbers = arrayOf("2048", "4096", "8192", "128", "256", "512", "1024")
            4096 -> numbers = arrayOf("4096", "8192", "128", "256", "512", "1024", "2048")
            8192 -> numbers = arrayOf("8192", "128", "256", "512", "1024", "2048", "4096")
        }

        vb.target.text = pref.target
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vb.picker.textColor = ContextCompat.getColor(requireContext(), R.color.white)
        }
        vb.picker.minValue = 0
        vb.picker.maxValue = numbers.size-1
        vb.picker.displayedValues = numbers
        vb.picker.setOnValueChangedListener { picker, oldValue, newValue ->
            vb.target.text = picker.displayedValues[newValue]
            pref.target = picker.displayedValues[newValue]
            pref.started = true
        }

        vb.btnContinue.setOnClickListener {
            pref.position = 2
            findNavController().navigate(R.id.action_targetScreen_to_mainScreen2)
        }
        vb.btnStart.setOnClickListener {
            pref.isFinish = false
            pref.isWin = false
            pref.position = 1
            pref.score = 0
            pref.movement = 0
            pref.started = true

            viewModel.startGameByTarget()
            findNavController().navigate(R.id.action_targetScreen_to_mainScreen2)
        }
        vb.btnInfo.setOnClickListener {
            findNavController().navigate(R.id.action_targetScreen_to_infoScreen)
        }

        viewModel.targetLiveData.observe(viewLifecycleOwner,targetObserver)

    }
    private val targetObserver = Observer <Boolean>{

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}