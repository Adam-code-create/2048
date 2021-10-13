package uz.gita.a2048

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val graph =navHost.navController.navInflater.inflate(R.navigation.nav_app)
        navHost.navController.graph = graph

    }


}