package com.example.sweatersleague.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sweatersleague.data.RepositoryImpl
import com.example.sweatersleague.domain.useCases.GetSummonerByNameUseCase
import com.example.sweatersleague.domain.useCases.GetSummonerLeagueByIdUseCase
import com.example.sweatersleague.domain.Repository
import com.example.sweatersleague.domain.Summoner
import com.example.sweatersleague.domain.useCases.GetSummonerMatchById
import com.example.sweatersleague.domain.useCases.GetSummonerMatchesIds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val repository: Repository = RepositoryImpl()

    private val getSummonerByNameUseCase = GetSummonerByNameUseCase(repository)
    private val getSummonerLeagueUseCase = GetSummonerLeagueByIdUseCase(repository)
    private val getMatchesIdsUseCase = GetSummonerMatchesIds(repository)
    private val getMatchByMatchId = GetSummonerMatchById(repository)

    private val _matchesIdListLD: MutableLiveData<MutableList<String>> = MutableLiveData()
    val matchesIdListLd: MutableLiveData<MutableList<String>>
        get() {
            return _matchesIdListLD
        }

    private val _summoner: MutableLiveData<Summoner> = MutableLiveData()
    val summoner: MutableLiveData<Summoner>
        get() {
            return _summoner
        }

    fun getSummonerByName(name: String) {
        viewModelScope.launch {
            val summoner = withContext(Dispatchers.Default){
                getSummonerByNameUseCase.getSummonerByName(name)
            }
            _summoner.value = summoner
        }
    }

    fun getMatchesByPuuId(puuId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                _matchesIdListLD.value?.addAll(getMatchesIdsUseCase.getSummonerMatchesIds(puuId))
            }
        }
    }

    fun getMatchByMatchId(matchId: String) {
        viewModelScope.launch {
            getMatchByMatchId.getSummonerMatch(matchId)
        }
    }
}