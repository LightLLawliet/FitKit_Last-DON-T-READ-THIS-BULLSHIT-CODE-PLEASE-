package com.example.fitkit_last.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.fitkit_last.MainActivity
import com.example.fitkit_last.databinding.ActivitySearchBinding
import com.example.fitkit_last.side_funcs.replaceActivity

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchProcess()
    }

    override fun onStart() {
        super.onStart()
        backPressed()
    }

    private fun searchProcess() {
        val user = arrayOf("Home", "About", "Exercises", "Prices", "Contact", "More")
        val userAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, user
        )
        binding.suggestList.adapter = userAdapter
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (user.contains(query)) {
                    userAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun backPressed() {
        binding.backButton.setOnClickListener {
            replaceActivity(MainActivity())
        }
    }
}