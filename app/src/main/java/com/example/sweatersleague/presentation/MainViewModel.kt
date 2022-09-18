package com.example.sweatersleague.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweatersleague.data.RepositoryImpl
import com.example.sweatersleague.domain.Repository
import com.example.sweatersleague.domain.Summoner
import com.example.sweatersleague.domain.lolMatch.LolMatch
import com.example.sweatersleague.domain.useCases.*
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    private val repository: Repository = RepositoryImpl()

    private val getSummonerByNameUseCase = GetSummonerByNameUseCase(repository)
    private val getSummonerLeagueUseCase = GetSummonerLeagueByIdUseCase(repository)
    private val getMatchesIdsUseCase = GetSummonerMatchesIds(repository)
    private val getMatchByMatchId = GetSummonerMatchesByIds(repository)

    private val _summoner: MutableLiveData<Summoner> = MutableLiveData()
    val summoner: MutableLiveData<Summoner>
        get() {
            return _summoner
        }

    var matchesIds: List<String>? = listOf()

    fun getSummonerByName(name: String) {
        viewModelScope.launch {
            val summoner = withContext(Dispatchers.Default) {
                getSummonerByNameUseCase.getSummonerByName(name)
            }
            if (summoner != null) {
                getMatchesByPuuId(summoner.puuId)
            }
            _summoner.value = summoner
        }
    }

    fun getMatchesByPuuId(puuId: String) {
        viewModelScope.launch {
            matchesIds = withContext(Dispatchers.Default) {
                getMatchesIdsUseCase.getSummonerMatchesIds(puuId)
            }
            matchesIds?.let { getMatchesByMatchIds(it) }
            Log.d("Matches", matchesIds.toString())
        }
    }

    fun getMatchesByMatchIds(matchesIds: List<String>) {
        viewModelScope.launch {
            matches.value = getMatchByMatchId.getSummonerMatches(matchesIds)
        }
    }

    companion object {
        var matches: MutableLiveData<List<LolMatch>> = MutableLiveData()
    }
}