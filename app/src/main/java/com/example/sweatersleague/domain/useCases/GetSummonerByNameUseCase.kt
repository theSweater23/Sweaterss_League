package com.example.sweatersleague.domain.useCases

import com.example.sweatersleague.domain.Repository
import com.example.sweatersleague.domain.Summoner

class GetSummonerByNameUseCase(private val repository: Repository) {

    fun getSummonerByName(name: String): Summoner? {
        return repository.getSummonerByName(name)
    }
}