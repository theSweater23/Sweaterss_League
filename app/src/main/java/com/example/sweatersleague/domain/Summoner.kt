package com.example.sweatersleague.domain

/**
 * Represents a League of Legends player account information.
 *
 * @property name Player's account nickname in League of Legends.
 * @property summonerLevel Level of player's account.
 * @property profileIconId ID of profile icon. Used to find image icon by its ID.
 * @property encryptedSummonerId Encrypted player's ID. Used to find player's champion masteries.
 * @property puuId Player's PUUID. Used to find player's match history.
 */

data class Summoner(
    val name: String,
    val summonerLevel: Int,
    val profileIconId: Int,
    val encryptedSummonerId: String,
    val puuId: String
)