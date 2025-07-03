package com.example.simplerecipe

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val listView = findViewById<ListView>(R.id.favoriteListView)
        val favSet = getSharedPreferences("favorite_prefs", MODE_PRIVATE).getStringSet("favorites", setOf())?.toMutableSet() ?: mutableSetOf()
        val favList = favSet.toMutableList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, favList)
        listView.adapter = adapter

        // Long click to remove from favorites
        listView.setOnItemLongClickListener { _, _, position, _ ->
            val toRemove = favList[position]
            favSet.remove(toRemove)
            favList.removeAt(position)
            getSharedPreferences("favorite_prefs", MODE_PRIVATE).edit().putStringSet("favorites", favSet).apply()
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "$toRemove dihapus dari favorit", Toast.LENGTH_SHORT).show()
            true
        }

        findViewById<Button?>(R.id.btnBack)?.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        // You can add logic here if needed
    }

    override fun onResume() {
        super.onResume()
        // You can add logic here if needed
    }

    override fun onPause() {
        super.onPause()
        // You can add logic here if needed
    }

    override fun onStop() {
        super.onStop()
        // You can add logic here if needed
    }

    override fun onDestroy() {
        super.onDestroy()
        // You can add logic here if needed
    }
}