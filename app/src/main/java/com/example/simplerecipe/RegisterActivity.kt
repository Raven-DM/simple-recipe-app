package com.example.simplerecipe

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val prefs = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        btnRegister.setOnClickListener {
            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                prefs.edit().putString("name", name)
                    .putString("email", email)
                    .putString("password", password)
                    .apply()
                Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button?>(R.id.btnBack)?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "RegisterActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "RegisterActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "RegisterActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "RegisterActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "RegisterActivity onDestroy")
    }
}