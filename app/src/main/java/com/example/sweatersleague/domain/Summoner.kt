package com.example.sweatersleague.domain

data class Summoner(
    val name: String,
    val summonerLevel: Int,
    val profileIconId: Int,
    val encryptedSummonerId: String,
    val puuId: String
)