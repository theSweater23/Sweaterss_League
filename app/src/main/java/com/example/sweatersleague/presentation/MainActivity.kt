package com.example.sweatersleague.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweatersleague.R
import com.example.sweatersleague.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewBinding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainViewBinding.root

        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val fragment = SummonerInfoFragment()
        setupSearchBar()

        mainViewModel.summoner.observe(this) {
            val bundle = Bundle()

            bundle.putString("summoner", it.name)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
            mainViewBinding.searchBar.isIconified = true
            mainViewBinding.searchBar.isIconified = true
        }
    }

    private fun setupSearchBar() {
        mainViewBinding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    mainViewModel.getSummonerByName(query)
                    true
                } else {
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}