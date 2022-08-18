package com.example.sweatersleague.domain.useCases

import com.example.sweatersleague.domain.LolMatch
import com.example.sweatersleague.domain.Repository

class GetSummonerMatchById(private val repository: Repository) {

    fun getSummonerMatch(matchId: String): LolMatch {
        return repository.getSummonerMatchByMatchId(matchId)
    }
}