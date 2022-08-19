package com.example.sweatersleague.domain.lolMatch.teamInfo

/**
 * @property kills Number of objective kills.
 */

data class Objective(
    val first: Boolean,
    val kills: Int
)