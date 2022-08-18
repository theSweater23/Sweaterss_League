package com.example.sweatersleague.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        setupSearchBar(fragment)
    }

    private fun setupSearchBar(fragment: Fragment) {
        mainViewBinding.searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null) {
                    mainViewModel.getSummonerByName(query)
                    mainViewModel.getMatchesByPuuId("y0pOcxN7AXR5SpRE1Bynea6CSjBtnylSehwRmIRPPd9pWGOuOW0HlSM-KB81jI1FYJOe5t-CGi9_EA")
                    mainViewModel.getMatchByMatchId("RU_404450625")

                    val bundle = Bundle()
                    bundle.putString("summoner", query)
                    fragment.arguments = bundle
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit()
                    mainViewBinding.searchBar.isIconified = true
                    mainViewBinding.searchBar.isIconified = true
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