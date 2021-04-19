package com.example.footpredict.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fixture(
    @SerialName("api")
    val api: Api
) {
    @Serializable
    data class Api(
        @SerialName("fixtures")
        val fixtures: List<Fixture>,
        @SerialName("results")
        val results: Int
    ) {
        @Serializable
        data class Fixture(
            @SerialName("awayTeam")
            val awayTeam: AwayTeam,
            @SerialName("elapsed")
            val elapsed: Int,
            @SerialName("event_date")
            val event_date: String,
            @SerialName("event_timestamp")
            val eventTimestamp: Int,
            @SerialName("events")
            val events: List<Event>,
            @SerialName("firstHalfStart")
            val firstHalfStart: Int,
            @SerialName("fixture_id")
            val fixture_id: Int,
            @SerialName("goalsAwayTeam")
            val goalsAwayTeam: Int,
            @SerialName("goalsHomeTeam")
            val goalsHomeTeam: Int,
            @SerialName("homeTeam")
            val homeTeam: HomeTeam,
            @SerialName("league")
            val league: League,
            @SerialName("league_id")
            val league_id: Int,
            @SerialName("lineups")
            val lineups: Lineups,
            @SerialName("players")
            val players: List<Player>,
            @SerialName("referee")
            val referee: String,
            @SerialName("round")
            val round: String,
            @SerialName("score")
            val score: Score,
            @SerialName("secondHalfStart")
            val secondHalfStart: Int,
            @SerialName("statistics")
            val statistics: Statistics,
            @SerialName("status")
            val status: String,
            @SerialName("statusShort")
            val statusShort: String,
            @SerialName("venue")
            val venue: String
        ) {
            @Serializable
            data class AwayTeam(
                @SerialName("logo")
                val logo: String,
                @SerialName("team_id")
                val team_id: Int,
                @SerialName("team_name")
                val team_name: String
            )

            @Serializable
            data class Event(
                @SerialName("assist")
                val assist: Any,
                @SerialName("assist_id")
                val assistId: Any,
                @SerialName("comments")
                val comments: Any,
                @SerialName("detail")
                val detail: String,
                @SerialName("elapsed")
                val elapsed: Int,
                @SerialName("elapsed_plus")
                val elapsedPlus: Any,
                @SerialName("player")
                val player: String,
                @SerialName("player_id")
                val playerId: Int,
                @SerialName("team_id")
                val teamId: Int,
                @SerialName("teamName")
                val teamName: String,
                @SerialName("type")
                val type: String
            )

            @Serializable
            data class HomeTeam(
                @SerialName("logo")
                val logo: String,
                @SerialName("team_id")
                val team_id: Int,
                @SerialName("team_name")
                val team_name: String
            )

            @Serializable
            data class League(
                @SerialName("country")
                val country: String,
                @SerialName("flag")
                val flag: String,
                @SerialName("logo")
                val logo: String,
                @SerialName("name")
                val name: String
            )

            @Serializable
            data class Lineups(
                @SerialName("Bordeaux")
                val bordeaux: Bordeaux,
                @SerialName("Saint Etienne")
                val saintEtienne: SaintEtienne
            ) {
                @Serializable
                data class Bordeaux(
                    @SerialName("coach")
                    val coach: String,
                    @SerialName("coach_id")
                    val coachId: Int,
                    @SerialName("formation")
                    val formation: String,
                    @SerialName("startXI")
                    val startXI: List<StartXI>,
                    @SerialName("substitutes")
                    val substitutes: List<Substitute>
                ) {
                    @Serializable
                    data class StartXI(
                        @SerialName("number")
                        val number: Int,
                        @SerialName("player")
                        val player: String,
                        @SerialName("player_id")
                        val playerId: Int,
                        @SerialName("pos")
                        val pos: String,
                        @SerialName("team_id")
                        val teamId: Int
                    )

                    @Serializable
                    data class Substitute(
                        @SerialName("number")
                        val number: Int,
                        @SerialName("player")
                        val player: String,
                        @SerialName("player_id")
                        val playerId: Int,
                        @SerialName("pos")
                        val pos: String,
                        @SerialName("team_id")
                        val teamId: Int
                    )
                }

                @Serializable
                data class SaintEtienne(
                    @SerialName("coach")
                    val coach: String,
                    @SerialName("coach_id")
                    val coachId: Int,
                    @SerialName("formation")
                    val formation: String,
                    @SerialName("startXI")
                    val startXI: List<StartXI>,
                    @SerialName("substitutes")
                    val substitutes: List<Substitute>
                ) {
                    @Serializable
                    data class StartXI(
                        @SerialName("number")
                        val number: Int,
                        @SerialName("player")
                        val player: String,
                        @SerialName("player_id")
                        val playerId: Int,
                        @SerialName("pos")
                        val pos: String,
                        @SerialName("team_id")
                        val teamId: Int
                    )

                    @Serializable
                    data class Substitute(
                        @SerialName("number")
                        val number: Int,
                        @SerialName("player")
                        val player: String,
                        @SerialName("player_id")
                        val playerId: Int,
                        @SerialName("pos")
                        val pos: String,
                        @SerialName("team_id")
                        val teamId: Int
                    )
                }
            }

            @Serializable
            data class Player(
                @SerialName("captain")
                val captain: String,
                @SerialName("cards")
                val cards: Cards,
                @SerialName("dribbles")
                val dribbles: Dribbles,
                @SerialName("duels")
                val duels: Duels,
                @SerialName("event_id")
                val eventId: Int,
                @SerialName("fouls")
                val fouls: Fouls,
                @SerialName("goals")
                val goals: Goals,
                @SerialName("minutes_played")
                val minutesPlayed: Int,
                @SerialName("number")
                val number: Int,
                @SerialName("offsides")
                val offsides: Any,
                @SerialName("passes")
                val passes: Passes,
                @SerialName("penalty")
                val penalty: Penalty,
                @SerialName("player_id")
                val playerId: Int,
                @SerialName("player_name")
                val playerName: String,
                @SerialName("position")
                val position: String,
                @SerialName("rating")
                val rating: String,
                @SerialName("shots")
                val shots: Shots,
                @SerialName("substitute")
                val substitute: String,
                @SerialName("tackles")
                val tackles: Tackles,
                @SerialName("team_id")
                val teamId: Int,
                @SerialName("team_name")
                val teamName: String,
                @SerialName("updateAt")
                val updateAt: Int
            ) {
                @Serializable
                data class Cards(
                    @SerialName("red")
                    val red: Int,
                    @SerialName("yellow")
                    val yellow: Int
                )

                @Serializable
                data class Dribbles(
                    @SerialName("attempts")
                    val attempts: Int,
                    @SerialName("past")
                    val past: Int,
                    @SerialName("success")
                    val success: Int
                )

                @Serializable
                data class Duels(
                    @SerialName("total")
                    val total: Int,
                    @SerialName("won")
                    val won: Int
                )

                @Serializable
                data class Fouls(
                    @SerialName("committed")
                    val committed: Int,
                    @SerialName("drawn")
                    val drawn: Int
                )

                @Serializable
                data class Goals(
                    @SerialName("assists")
                    val assists: Int,
                    @SerialName("conceded")
                    val conceded: Int,
                    @SerialName("saves")
                    val saves: Int,
                    @SerialName("total")
                    val total: Int
                )

                @Serializable
                data class Passes(
                    @SerialName("accuracy")
                    val accuracy: Int,
                    @SerialName("key")
                    val key: Int,
                    @SerialName("total")
                    val total: Int
                )

                @Serializable
                data class Penalty(
                    @SerialName("commited")
                    val commited: Int,
                    @SerialName("missed")
                    val missed: Int,
                    @SerialName("saved")
                    val saved: Int,
                    @SerialName("success")
                    val success: Int,
                    @SerialName("won")
                    val won: Int
                )

                @Serializable
                data class Shots(
                    @SerialName("on")
                    val on: Int,
                    @SerialName("total")
                    val total: Int
                )

                @Serializable
                data class Tackles(
                    @SerialName("blocks")
                    val blocks: Int,
                    @SerialName("interceptions")
                    val interceptions: Int,
                    @SerialName("total")
                    val total: Int
                )
            }

            @Serializable
            data class Score(
                @SerialName("extratime")
                val extratime: Any,
                @SerialName("fulltime")
                val fulltime: String,
                @SerialName("halftime")
                val halftime: String,
                @SerialName("penalty")
                val penalty: Any
            )

            @Serializable
            data class Statistics(
                @SerialName("Ball Possession")
                val ballPossession: BallPossession,
                @SerialName("Blocked Shots")
                val blockedShots: BlockedShots,
                @SerialName("Corner Kicks")
                val cornerKicks: CornerKicks,
                @SerialName("Fouls")
                val fouls: Fouls,
                @SerialName("Goalkeeper Saves")
                val goalkeeperSaves: GoalkeeperSaves,
                @SerialName("Offsides")
                val offsides: Offsides,
                @SerialName("Passes %")
                val passes: Passes,
                @SerialName("Passes accurate")
                val passesAccurate: PassesAccurate,
                @SerialName("Red Cards")
                val redCards: RedCards,
                @SerialName("Shots insidebox")
                val shotsInsidebox: ShotsInsidebox,
                @SerialName("Shots off Goal")
                val shotsOffGoal: ShotsOffGoal,
                @SerialName("Shots on Goal")
                val shotsOnGoal: ShotsOnGoal,
                @SerialName("Shots outsidebox")
                val shotsOutsidebox: ShotsOutsidebox,
                @SerialName("Total passes")
                val totalPasses: TotalPasses,
                @SerialName("Total Shots")
                val totalShots: TotalShots,
                @SerialName("Yellow Cards")
                val yellowCards: YellowCards
            ) {
                @Serializable
                data class BallPossession(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class BlockedShots(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class CornerKicks(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class Fouls(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class GoalkeeperSaves(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class Offsides(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class Passes(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class PassesAccurate(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class RedCards(
                    @SerialName("away")
                    val away: Any,
                    @SerialName("home")
                    val home: Any
                )

                @Serializable
                data class ShotsInsidebox(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class ShotsOffGoal(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class ShotsOnGoal(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class ShotsOutsidebox(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class TotalPasses(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class TotalShots(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class YellowCards(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )
            }
        }
    }
}