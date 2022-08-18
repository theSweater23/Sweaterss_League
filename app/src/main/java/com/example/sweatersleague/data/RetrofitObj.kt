package com.example.sweatersleague.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObj {
    private val retrofitForPlatform: Retrofit = Retrofit.Builder()
        .baseUrl("https://ru.api.riotgames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitForRegion: Retrofit = Retrofit.Builder()
        .baseUrl("https://europe.api.riotgames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val platformService: PlatformService = retrofitForPlatform.create(PlatformService::class.java)
    val regionService: RegionService = retrofitForRegion.create(RegionService::class.java)
}