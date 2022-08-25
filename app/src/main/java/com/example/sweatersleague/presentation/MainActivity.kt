package com.example.sweatersleague.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
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

        hideSystemBars()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupSearchBar()
        val fragment = SummonerInfoFragment()
        mainViewModel.summoner.observe(this) {
            val bundle = Bundle()
            if (it == null) {
                bundle.putString(SummonerInfoFragment.SUMMONER, "null")
            } else {
                bundle.putString(SummonerInfoFragment.SUMMONER, it.name)
                //mainViewModel.getMatchesByPuuId(it.puuId)
            }
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
            mainViewBinding.searchBar.isIconified = true
            mainViewBinding.searchBar.isIconified = true
        }

        mainViewModel.matchesIdListLd.observe(this) {
            Log.d("Matches", "afa")
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

    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}