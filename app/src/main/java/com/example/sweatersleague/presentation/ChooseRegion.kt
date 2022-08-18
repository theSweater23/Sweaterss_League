package com.example.sweatersleague.presentation

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.example.sweatersleague.R
import com.example.sweatersleague.databinding.ActivityChooseRegionBinding

class ChooseRegion : AppCompatActivity() {
    private lateinit var _binding: ActivityChooseRegionBinding
    private val binding: ActivityChooseRegionBinding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChooseRegionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.regionRU.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}