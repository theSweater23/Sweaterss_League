package com.example.sweatersleague.domain.lolMatch

import com.example.sweatersleague.domain.lolMatch.participant.*
import com.example.sweatersleague.domain.lolMatch.participant.perks.Perks
import com.example.sweatersleague.domain.lolMatch.participant.perks.Stats
import com.example.sweatersleague.domain.lolMatch.participant.perks.Style
import com.example.sweatersleague.domain.lolMatch.participant.perks.Styles
import com.example.sweatersleague.domain.lolMatch.teamInfo.Objective
import com.example.sweatersleague.domain.lolMatch.teamInfo.Objectives
import com.example.sweatersleague.domain.lolMatch.teamInfo.TeamInfo

/**
 * Represents a League of Legends match.
 *
 * @property gameDuration Duration of game.
 * @property participants List of players who participated in the match.
 * @property teamsInfo Information about teams results.
 */

data class LolMatch(
    val gameDuration: Long,
    val participants: List<Participant>,
    val teamsInfo: List<TeamInfo>
)