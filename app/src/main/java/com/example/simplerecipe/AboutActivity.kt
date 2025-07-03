package com.example.simplerecipe

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        findViewById<TextView>(R.id.tvPembuat).text = "Dibuat oleh: Raven dan Dena"
        findViewById<TextView>(R.id.tvVersi).text = "Versi Aplikasi: 1.0"

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "AboutActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "AboutActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "AboutActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "AboutActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "AboutActivity onDestroy")
    }
}