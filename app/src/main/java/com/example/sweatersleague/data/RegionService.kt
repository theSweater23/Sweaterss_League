package com.example.sweatersleague.data

import com.example.sweatersleague.Constants
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RegionService {

    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids?start=0&count=1&api_key=${Constants.API_KEY}")
    fun getSummonerMatches(@Path("puuid") puuId: String): Call<JsonArray>

    @GET("lol/match/v5/matches/{matchId}?api_key=${Constants.API_KEY}")
    suspend fun getSummonerMatchById(@Path("matchId") matchId: String): Response<JsonObject>
}