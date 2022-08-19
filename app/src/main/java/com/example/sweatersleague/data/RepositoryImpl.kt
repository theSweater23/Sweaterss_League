package com.example.sweatersleague.data

import android.util.Log
import com.example.sweatersleague.domain.lolMatch.LolMatch
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

        val response = RetrofitObj.platformService.getSummonerByName(name).execute()
        summoner = response.body()?.let { formSummonerFromJson(it) }

        Log.d("Summoner", response.body().toString())

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

        val response = RetrofitObj.regionService.getSummonerMatches(summonerPuuId).execute()
        for(element in response.body()!!) {
            result.add(element.asString)
        }
        Log.d("Matches", result.toString())

        return result.toList()
    }

    override fun getSummonerMatchByMatchId(matchId: String): LolMatch {

        val response = RetrofitObj.regionService.getSummonerMatchById(matchId).execute()
        Log.d("Match Info", response.body().toString())

        return LolMatch.LolMatchTest
    }
}