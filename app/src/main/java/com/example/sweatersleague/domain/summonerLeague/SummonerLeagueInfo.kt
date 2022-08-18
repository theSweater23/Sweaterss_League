package com.example.sweatersleague.domain.summonerLeague

data class SummonerLeagueInfo (
    val queueType: QueueType,
    val tier: LeagueTier,
    val rank: String,
    val wins: Int,
    val losses: Int
)