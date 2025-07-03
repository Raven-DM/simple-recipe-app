package com.example.simplerecipe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RecipeListActivity : AppCompatActivity() {
    private val recipes = mutableListOf<String>()
    private val descMap = mutableMapOf<String, String>()
    private val favoritePrefs by lazy { getSharedPreferences("favorite_prefs", MODE_PRIVATE) }
    private var selectedRecipe: String? = null
    private var selectedDesc: String? = null
    private val EDIT_RECIPE_REQUEST = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        val listView = findViewById<ListView>(R.id.recipeListView)

        // Load recipes from SharedPreferences
        val recipePrefs = getSharedPreferences("recipe_prefs", MODE_PRIVATE)
        val savedRecipes = recipePrefs.getStringSet("recipes", null)
        val savedDescs = recipePrefs.getString("descs", null)
        if (savedRecipes != null) recipes.addAll(savedRecipes)
        else recipes.addAll(listOf("Nasi Goreng", "Soto Ayam", "Mie Goreng", "Ayam Bakar", "Bakso"))
        if (savedDescs != null) {
            val map = savedDescs.split(";;;").mapNotNull {
                val parts = it.split(":::")
                if (parts.size == 2) parts[0] to parts[1] else null
            }.toMap()
            descMap.putAll(map)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, recipes)
        listView.adapter = adapter

        // Add new recipe from intent if available
        val newName = intent.getStringExtra("RECIPE_NAME")
        val newDesc = intent.getStringExtra("RECIPE_DESC")
        if (!newName.isNullOrEmpty()) {
            recipes.add(newName)
            descMap[newName] = newDesc ?: ""
            // Save to SharedPreferences
            recipePrefs.edit().putStringSet("recipes", recipes.toSet()).putString("descs", descMap.entries.joinToString(";;;") { it.key+":::"+it.value }).apply()
            adapter.notifyDataSetChanged()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            selectedRecipe = recipes[position]
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("RECIPE_NAME", recipes[position])
            intent.putExtra("RECIPE_DESC", descMap[recipes[position]] ?: "")
            startActivity(intent)
        }

        findViewById<Button?>(R.id.btnAddToFavorite)?.setOnClickListener {
            selectedRecipe?.let {
                val favSet = favoritePrefs.getStringSet("favorites", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
                favSet.add(it)
                favoritePrefs.edit().putStringSet("favorites", favSet).apply()
                Toast.makeText(this, "$it ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
            } ?: Toast.makeText(this, "Pilih resep terlebih dahulu", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button?>(R.id.btnBack)?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<Button?>(R.id.btnEditRecipe)?.setOnClickListener {
            if (selectedRecipe != null) {
                val intent = Intent(this, AddRecipeActivity::class.java)
                intent.putExtra("RECIPE_NAME", selectedRecipe)
                intent.putExtra("RECIPE_DESC", descMap[selectedRecipe])
                startActivityForResult(intent, EDIT_RECIPE_REQUEST)
            } else {
                Toast.makeText(this, "Pilih resep yang ingin diedit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "RecipeListActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "RecipeListActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "RecipeListActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "RecipeListActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "RecipeListActivity onDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_RECIPE_REQUEST && resultCode == RESULT_OK && data != null) {
            val oldName = selectedRecipe
            val newName = data.getStringExtra("RECIPE_NAME")
            val newDesc = data.getStringExtra("RECIPE_DESC")
            if (!newName.isNullOrEmpty() && oldName != null) {
                val idx = recipes.indexOf(oldName)
                if (idx != -1) {
                    recipes[idx] = newName
                    descMap.remove(oldName)
                    descMap[newName] = newDesc ?: ""
                    // Save to SharedPreferences
                    val recipePrefs = getSharedPreferences("recipe_prefs", MODE_PRIVATE)
                    recipePrefs.edit().putStringSet("recipes", recipes.toSet()).putString("descs", descMap.entries.joinToString(";;;" ) { it.key+":::"+it.value }).apply()
                    (findViewById<ListView>(R.id.recipeListView).adapter as ArrayAdapter<*>).notifyDataSetChanged()
                }
            }
        }
    }
}