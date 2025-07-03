package com.example.simplerecipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        findViewById<TextView>(R.id.tvNama).text = "Nama: " + (prefs.getString("name", "-"))
        findViewById<TextView>(R.id.tvEmail).text = "Email: " + (prefs.getString("email", "-"))


        findViewById<Button?>(R.id.btnBack)?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "ProfileActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "ProfileActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "ProfileActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "ProfileActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "ProfileActivity onDestroy")
    }
}