package com.example.simplerecipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private val addRecipeLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK && result.data != null) {
            val name = result.data?.getStringExtra("RECIPE_NAME")
            val desc = result.data?.getStringExtra("RECIPE_DESC")
            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("RECIPE_NAME", name)
            intent.putExtra("RECIPE_DESC", desc)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.btnDaftarResep).setOnClickListener {
            startActivity(Intent(this, RecipeListActivity::class.java))
        }

        findViewById<Button>(R.id.btnTambahResep).setOnClickListener {
            addRecipeLauncher.launch(Intent(this, AddRecipeActivity::class.java))
        }

        findViewById<Button>(R.id.btnFavorit).setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
        }

        findViewById<Button>(R.id.btnProfil).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btnAbout).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "HomeActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "HomeActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "HomeActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "HomeActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "HomeActivity onDestroy")
    }
}