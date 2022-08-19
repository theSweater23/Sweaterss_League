package com.example.sweatersleague.domain.lolMatch.teamInfo

/**
 * Represents team information and statistics for current match.
 *
 * @property win If current team won the match, it equals true, else - false.
 * @property objectives Shows team objectives statistics.
 */

data class TeamInfo(
    val win: Boolean,
    val objectives: Objectives
)