package com.example.sweatersleague.domain.lolMatch.teamInfo

/**
 * Shows what objectives team have captured.
 *
 * @property baron Number of Baron kills.
 * @property champion Number of enemy champion kills.
 * @property dragon Number of dragon kills.
 * @property tower Number of towers destroyed.
 */

data class Objectives(
    val baron: Objective,
    val champion: Objective,
    val dragon: Objective,
    val tower: Objective
)