package com.example.sweatersleague.data

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlatformService {

    @GET("lol/summoner/v4/summoners/by-name/{summonerName}?api_key=$API_KEY")
    fun getSummonerByName(@Path("summonerName") name: String): Call<JsonObject>

    @GET("lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=$API_KEY")
    fun getSummonerLeague(@Path("encryptedSummonerId") id: String): Call<JsonObject>

    @GET("lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}?api_key=$API_KEY")
    fun getSummonerMasteries(@Path("encryptedSummonerId") id: String): Call<JsonObject>

    companion object {
        const val API_KEY = "RGAPI-ea91e127-8aa4-4bb9-8334-f28a1dac6ecc"
    }
}