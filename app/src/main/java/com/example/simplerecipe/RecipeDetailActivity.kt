package com.example.simplerecipe

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipeName = intent.getStringExtra("RECIPE_NAME") ?: "Resep Tidak Dikenal"
        val recipeDesc = intent.getStringExtra("RECIPE_DESC") ?: ""
        findViewById<TextView>(R.id.tvRecipeName).text = recipeName
        findViewById<TextView>(R.id.tvRecipeDesc).text = if (recipeDesc.isNotEmpty()) recipeDesc else "Ini adalah resep $recipeName (dummy)."

        findViewById<Button?>(R.id.btnBack)?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "RecipeDetailActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "RecipeDetailActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "RecipeDetailActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "RecipeDetailActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "RecipeDetailActivity onDestroy")
    }
}