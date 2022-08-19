package com.example.sweatersleague.domain

import com.example.sweatersleague.domain.lolMatch.LolMatch
import com.example.sweatersleague.domain.summonerLeague.SummonerLeagueInfo

interface Repository {

    /**
     * Returns a [Summoner].
     *
     * @return information about player account, his nickname, id, etc.
     */
    fun getSummonerByName(name: String): Summoner?

    /**
     * Returns a list of [SummonerLeagueInfo].
     *
     * @return list of player's leagues.
     */
    fun getSummonerLeagueById(encryptedSummonerId: String): List<SummonerLeagueInfo>

    /**
     * @return list of player's matches ID's.
     */
    fun getSummonerMatchesIds(summonerPuuId: String): List<String>

    /**
     * Returns a match information[LolMatch].
     *
     * @return match by match ID.
     */
    fun getSummonerMatchByMatchId(matchId: String): LolMatch
}