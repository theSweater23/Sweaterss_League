package com.example.sweatersleague.domain

data class Participant(
    val assists: Int,
    val champLevel: Int,
    val championName: String,
    val deaths: Int,
    val firstBloodKill: Boolean,
    val goldEarned: Int,
    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,
    val kills: Int,
    val lane: String,
    val profileIcon: Int,
    val role: String,
    val summonerLevel: Int,
    val summonerName: String
)