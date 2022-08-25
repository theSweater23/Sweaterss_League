package com.example.sweatersleague.domain.useCases

import com.example.sweatersleague.domain.Repository

class GetSummonerMatchesIds(private val repository: Repository) {

    fun getSummonerMatchesIds(puuId: String): List<String>? {
        return repository.getSummonerMatchesIds(puuId)
    }
}