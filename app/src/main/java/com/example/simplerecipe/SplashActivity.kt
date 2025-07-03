package com.example.simplerecipe

import android.content.Intent
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Log.d("LIFECYCLE", "SplashActivity onCreate")

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "SplashActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "SplashActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "SplashActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "SplashActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "SplashActivity onDestroy")
    }
}