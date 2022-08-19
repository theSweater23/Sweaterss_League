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
 * @property teamInfo Information about teams results.
 */

data class LolMatch(
    val gameDuration: Long,
    val participants: List<Participant>,
    val teamInfo: TeamInfo
){
    companion object {
        val LolMatchTest: LolMatch = LolMatch(
            60,
            listOf(
                Participant(
                0,
                0,
                "",
                0,
                false,
                1000,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
            "",
            0,
            "",
            0,
            "",
            perks = Perks(
                Stats(10,10,10),
                Styles(
                    listOf(Style(1,1,1,1))
                )
            )
            )
            ),
            TeamInfo(true, Objectives(
                Objective(false, 0),
                Objective(false, 0),
                Objective(false, 0),
                Objective(false, 0)
            )
            )
        )
    }
}