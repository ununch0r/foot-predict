package com.example.footpredict.data

data class ApiResponse(
    val api: Api
) {
    data class Api(
        val fixtures: List<Fixture>,
        val results: Int
    ) {
        data class Fixture(
            val fixture_id : Int,
            val awayTeam: AwayTeam,
            val event_date: String,
            val homeTeam: HomeTeam,
        ) {
            data class AwayTeam(
                val logo: String,
                val team_id: Int,
                val team_name: String
            )

            data class HomeTeam(
                val logo: String,
                val team_id: Int,
                val team_name: String
            )

            data class League(
                val country: String,
                val flag: String,
                val logo: String,
                val name: String
            )

            data class Score(
                val extratime: Any,
                val fulltime: Any,
                val halftime: Any,
                val penalty: Any
            )
        }
    }
}