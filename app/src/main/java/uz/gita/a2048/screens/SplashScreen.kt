package uz.gita.a2048.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.a2048.R

@SuppressLint("CustomSplashScreen")
class SplashScreen :Fragment(R.layout.screen_splash)  {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       Handler(Looper.getMainLooper()).postDelayed({ findNavController().navigate(R.id.action_splashScreen_to_targetScreen) }, 2000)

    }
}