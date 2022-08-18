package com.example.sweatersleague.data

import android.util.Log
import com.example.sweatersleague.domain.LolMatch
import com.example.sweatersleague.domain.Repository
import com.example.sweatersleague.domain.Summoner
import com.example.sweatersleague.domain.summonerLeague.SummonerLeagueInfo
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException

class RepositoryImpl: Repository {

    override fun getSummonerByName(name: String): Summoner? {
        var summoner: Summoner? = Summoner("",1,1, "No id", "")

        RetrofitObj.platformService.getSummonerByName(name).enqueue(
            object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    val summonerJsonObject = response.body()
                    if (summonerJsonObject != null) {
                        summoner = formSummonerFromJson(summonerJsonObject)
                    }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                throw RuntimeException(t.message)
            }
        })
        return summoner
    }

    private fun formSummonerFromJson(summonerJson: JsonObject): Summoner {
        return Summoner(
            name = summonerJson.get("name").toString(),
            summonerLevel = summonerJson.get("summonerLevel").asInt,
            profileIconId = summonerJson.get("profileIconId").asInt,
            encryptedSummonerId = summonerJson.get("id").asString,
            puuId = summonerJson.get("puuid").asString
        )
    }

    override fun getSummonerLeagueById(encryptedSummonerId: String): List<SummonerLeagueInfo> {
        TODO("Not implemented yet!")
    }

    override fun getSummonerMatchesIds(summonerPuuId: String): List<String> {
        val result = mutableListOf<String>()
        RetrofitObj.regionService.getSummonerMatches(summonerPuuId).enqueue(
            object: Callback<JsonArray> {
                override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                    for(element in response.body()!!) {
                        result.add(element.asString)
                    }
                    Log.d("Matches", result.toString())
                }

                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    throw RuntimeException(t.message)
                }
            }
        )
        return result.toList()
    }

    override fun getSummonerMatchByMatchId(matchId: String): LolMatch {
        RetrofitObj.regionService.getSummonerMatchById(matchId).enqueue(
            object: Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    Log.d("Match Info", response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    throw RuntimeException(t.message)
                }
            }
        )
        return LolMatch.LolMatchTest
    }
}