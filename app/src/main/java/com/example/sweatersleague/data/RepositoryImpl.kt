package com.example.sweatersleague.data

import android.util.Log
import com.example.sweatersleague.domain.lolMatch.LolMatch
import com.example.sweatersleague.domain.Repository
import com.example.sweatersleague.domain.Summoner
import com.example.sweatersleague.domain.lolMatch.participant.Participant
import com.example.sweatersleague.domain.lolMatch.participant.perks.Perks
import com.example.sweatersleague.domain.lolMatch.participant.perks.Stats
import com.example.sweatersleague.domain.lolMatch.participant.perks.Style
import com.example.sweatersleague.domain.lolMatch.participant.perks.Styles
import com.example.sweatersleague.domain.lolMatch.teamInfo.Objective
import com.example.sweatersleague.domain.lolMatch.teamInfo.Objectives
import com.example.sweatersleague.domain.lolMatch.teamInfo.TeamInfo
import com.example.sweatersleague.domain.summonerLeague.SummonerLeagueInfo
import com.google.gson.JsonObject

class RepositoryImpl: Repository {

    override fun getSummonerByName(name: String): Summoner? {
        val response = RetrofitObj.platformService.getSummonerByName(name).execute()

        return if (response.isSuccessful) {
            Log.d("SummonerInfo", response.body().toString())
            response.body()?.let { formSummonerFromJson(it) }
        } else {
            Log.d("SummonerInfo", response.message())
            null
        }
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

    override fun getSummonerMatchesIds(summonerPuuId: String): List<String>? {
        val response = RetrofitObj.regionService.getSummonerMatches(summonerPuuId).execute()
        return if (response.isSuccessful) {
            response.body()?.joinToString { element ->
                element.toString().filter { it != '"' }
            }?.split(',')
        } else {
            null
        }
    }

    override suspend fun getSummonerMatchesByMatchIds(matchIds: List<String>): List<LolMatch> {
        val result = mutableListOf<LolMatch>()
        matchIds.forEach { matchId ->
            val response = RetrofitObj.regionService.getSummonerMatchById(matchId).body()
            result.add(formatMatchResponse(response!!))
        }
        return result
    }

    private fun formatMatchResponse(matchResponse: JsonObject): LolMatch {
        val gameDuration = matchResponse.getAsJsonObject("info").get("gameDuration").asLong / 60

        val participantsList = mutableListOf<Participant>()
        val participantsJson = matchResponse.getAsJsonObject("info").getAsJsonArray("participants")
        participantsJson.forEach { participant ->
            with(participant.asJsonObject) {
                participantsList.add(fromParticipant(this))
            }
        }

        val teamsInfoJson = matchResponse.getAsJsonArray("team")
        val teamsInfo = mutableListOf<TeamInfo>()
        teamsInfoJson.forEach { teamInfo ->
            with(teamInfo.asJsonObject) {
                teamsInfo.add(formTeamsInfo(this))
            }
        }
        return LolMatch(gameDuration, participantsList, teamsInfo)
    }

    private fun formTeamsInfo(teamInfoJsonObject: JsonObject): TeamInfo {
        return TeamInfo(
            win = teamInfoJsonObject.get("win").asBoolean,
            objectives = Objectives(
                baron = formObjective(teamInfoJsonObject, "baron"),
                champion = formObjective(teamInfoJsonObject, "champion"),
                dragon = formObjective(teamInfoJsonObject, "dragon"),
                tower = formObjective(teamInfoJsonObject, "tower")
            )
        )
    }

    private fun formObjective(teamInfo: JsonObject, objectiveName: String): Objective {
        return Objective(
            first = teamInfo.getAsJsonObject(objectiveName).get("first").asBoolean,
            kills = teamInfo.getAsJsonObject(objectiveName).get("kills").asInt
        )
    }

    private fun fromParticipant(participant: JsonObject): Participant {
        return Participant(
            assists = participant.get("assists").asInt,
            champLevel = participant.get("champLevel").asInt,
            championName = participant.get("championName").asString,
            championId = participant.get("championId").asInt,
            deaths = participant.get("deaths").asInt,
            firstBloodKill = participant.get("firstBloodKill").asBoolean,
            goldEarned = participant.get("goldEarned").asInt,
            item0 = participant.get("item0").asInt,
            item1 = participant.get("item1").asInt,
            item2 = participant.get("item2").asInt,
            item3 = participant.get("item3").asInt,
            item4 = participant.get("item4").asInt,
            item5 = participant.get("item5").asInt,
            item6 = participant.get("item6").asInt,
            kills = participant.get("kills").asInt,
            lane = participant.get("lane").asString,
            profileIcon = participant.get("profileIcon").asInt,
            role = participant.get("role").asString,
            summonerLevel = participant.get("summonerLevel").asInt,
            summonerName = participant.get("summonerName").asString,
            perks = formPerks(participant)
        )
    }

    private fun formPerks(participant: JsonObject): Perks {
        val statPerks = participant.getAsJsonObject("statPerks")
        val stats = Stats(
            defense = statPerks.get("defense").asInt,
            flex = statPerks.get("flex").asInt,
            offense = statPerks.get("offense").asInt
        )

        val stylesJson = participant.getAsJsonArray("styles")
        val stylesList = mutableListOf<Style>()
        stylesJson.forEach { selections ->
            selections.asJsonArray.forEach { styleJsonObject ->
                stylesList.add(formStyle(styleJsonObject.asJsonObject))
            }
        }
        val styles = Styles(stylesList)
        return Perks(stats, styles)
    }

    private fun formStyle(styleJsonObject: JsonObject): Style {
        return Style(
            perk = styleJsonObject.asJsonObject.get("perk").asInt,
            var1 = styleJsonObject.asJsonObject.get("var1").asInt,
            var2 = styleJsonObject.asJsonObject.get("var2").asInt,
            var3 = styleJsonObject.asJsonObject.get("var3").asInt
        )
    }
}