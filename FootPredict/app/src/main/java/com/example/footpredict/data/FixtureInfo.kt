package com.example.footpredict.data

data class FixtureInfo(
    val api: Api
) {
    data class Api(
        val fixtures: List<Fixture>,
        val results: Int
    ) {
        data class Fixture(
            val awayTeam: AwayTeam,
            val elapsed: Int,
            val event_date: String,
            val event_timestamp: Int,
            val events: List<Event>,
            val firstHalfStart: Int,
            val fixture_id: Int,
            val goalsAwayTeam: Int,
            val goalsHomeTeam: Int,
            val homeTeam: HomeTeam,
            val league: League,
            val league_id: Int,
            val players: List<Player>,
            val referee: String,
            val round: String,
            val score: Score,
            val secondHalfStart: Int,
            val status: String,
            val statusShort: String,
            val venue: String
        ) {
            data class AwayTeam(
                val logo: String,
                val team_id: Int,
                val team_name: String
            )

            data class Event(
                val assist: Any,
                val assist_id: Any,
                val comments: Any,
                val detail: String,
                val elapsed: Int,
                val elapsed_plus: Any,
                val player: String,
                val player_id: Int,
                val teamName: String,
                val team_id: Int,
                val type: String
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

            data class Player(
                val captain: String,
                val cards: Cards,
                val dribbles: Dribbles,
                val duels: Duels,
                val event_id: Int,
                val fouls: Fouls,
                val goals: Goals,
                val minutes_played: Int,
                val number: Int,
                val offsides: Any,
                val passes: Passes,
                val penalty: Penalty,
                val player_id: Int,
                val player_name: String,
                val position: String,
                val rating: String,
                val shots: Shots,
                val substitute: String,
                val tackles: Tackles,
                val team_id: Int,
                val team_name: String,
                val updateAt: Int
            ) {
                data class Cards(
                    val red: Int,
                    val yellow: Int
                )

                data class Dribbles(
                    val attempts: Int,
                    val past: Int,
                    val success: Int
                )

                data class Duels(
                    val total: Int,
                    val won: Int
                )

                data class Fouls(
                    val committed: Int,
                    val drawn: Int
                )

                data class Goals(
                    val assists: Int,
                    val conceded: Int,
                    val saves: Int,
                    val total: Int
                )

                data class Passes(
                    val accuracy: Int,
                    val key: Int,
                    val total: Int
                )

                data class Penalty(
                    val commited: Int,
                    val missed: Int,
                    val saved: Int,
                    val success: Int,
                    val won: Int
                )

                data class Shots(
                    val on: Int,
                    val total: Int
                )

                data class Tackles(
                    val blocks: Int,
                    val interceptions: Int,
                    val total: Int
                )
            }

            data class Score(
                val extratime: Any,
                val fulltime: String,
                val halftime: String,
                val penalty: Any
            )
        }
    }
}