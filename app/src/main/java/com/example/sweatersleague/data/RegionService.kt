package com.example.sweatersleague.data

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RegionService {

    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids?start=0&count=20&api_key=$API_KEY")
    fun getSummonerMatches(@Path("puuid") puuId: String): Call<JsonArray>

    @GET("lol/match/v5/matches/{matchId}?api_key=$API_KEY")
    fun getSummonerMatchById(@Path("matchId") matchId: String): Call<JsonObject>

    companion object {
        const val API_KEY = PlatformService.API_KEY
    }
}