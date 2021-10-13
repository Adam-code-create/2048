package uz.gita.a2048.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.a2048.R
import uz.gita.a2048.databinding.ScreenInfoBinding


class InfoScreen :Fragment(R.layout.screen_info) {
    private var _viewBinding : ScreenInfoBinding? = null
    private val vb get()  = _viewBinding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _viewBinding = ScreenInfoBinding.bind(view)

        vb.back.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}