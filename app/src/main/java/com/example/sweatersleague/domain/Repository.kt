package com.example.sweatersleague.domain

import com.example.sweatersleague.domain.summonerLeague.SummonerLeagueInfo

interface Repository {

    fun getSummonerByName(name: String): Summoner?

    fun getSummonerLeagueById(encryptedSummonerId: String): List<SummonerLeagueInfo>

    fun getSummonerMatchesIds(summonerPuuId: String): List<String>

    fun getSummonerMatchByMatchId(matchId: String): LolMatch
}