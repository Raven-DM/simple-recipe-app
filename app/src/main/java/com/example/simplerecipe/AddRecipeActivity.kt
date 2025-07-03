package com.example.simplerecipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val etName = findViewById<EditText>(R.id.etName)
        val etDesc = findViewById<EditText>(R.id.etDesc)
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        findViewById<Button>(R.id.btnSimpan).setOnClickListener {
            val name = etName.text.toString().trim()
            val desc = etDesc.text.toString().trim()
            if (name.isNotEmpty() && desc.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra("RECIPE_NAME", name)
                intent.putExtra("RECIPE_DESC", desc)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Nama dan deskripsi harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "AddRecipeActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "AddRecipeActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "AddRecipeActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "AddRecipeActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "AddRecipeActivity onDestroy")
    }
}