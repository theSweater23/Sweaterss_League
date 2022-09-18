package com.example.sweatersleague.domain.useCases

import com.example.sweatersleague.domain.lolMatch.LolMatch
import com.example.sweatersleague.domain.Repository

class GetSummonerMatchesByIds(private val repository: Repository) {

    suspend fun getSummonerMatches(matchesIds: List<String>): List<LolMatch> {
        return repository.getSummonerMatchesByMatchIds(matchesIds)
    }
}