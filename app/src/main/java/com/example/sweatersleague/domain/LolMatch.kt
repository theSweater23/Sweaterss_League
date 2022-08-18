package com.example.sweatersleague.domain

data class LolMatch(
    val gameDuration: Long,
    val participants: List<Participant>,
){
    companion object {
        val LolMatchTest: LolMatch = LolMatch(
            60,
            listOf(Participant(0,
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
            ""))
        )
    }
}