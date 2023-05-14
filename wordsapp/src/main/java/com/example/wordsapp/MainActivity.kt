package com.example.wordsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var isLinearLayoutManager = true
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Sets the LinearLayoutManager of the recyclerview
        chooseLayout()

    }

    private fun chooseLayout() {
        if (isLinearLayoutManager)
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        else
            binding.recyclerView.layoutManager = GridLayoutManager(this, 4)

        binding.recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem) {

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.view_list)
            else
                ContextCompat.getDrawable(this, R.drawable.module)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        val menuBtn = menu?.findItem(R.id.switch_layout)
        if (menuBtn != null)
            setIcon(menuBtn)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}