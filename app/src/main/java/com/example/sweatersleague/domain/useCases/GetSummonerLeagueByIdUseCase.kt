package com.example.sweatersleague.domain.useCases

import com.example.sweatersleague.domain.Repository
import com.example.sweatersleague.domain.summonerLeague.SummonerLeagueInfo

class GetSummonerLeagueByIdUseCase(private val repository: Repository) {

    fun getSummonerLeague(encryptedId: String): List<SummonerLeagueInfo> {
        return repository.getSummonerLeagueById(encryptedId)
    }
}