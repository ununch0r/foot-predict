package com.example.footpredict.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Prediction(
    @SerialName("api")
    val api: Api
) {
    @Serializable
    data class Api(
        @SerialName("predictions")
        val predictions: List<Prediction>,
        @SerialName("results")
        val results: Int
    ) {
        @Serializable
        data class Prediction(
            @SerialName("advice")
            val advice: String,
            @SerialName("comparison")
            val comparison: Comparison,
            @SerialName("goals_away")
            val goalsAway: String,
            @SerialName("goals_home")
            val goalsHome: String,
            @SerialName("h2h")
            val h2h: List<H2h>,
            @SerialName("match_winner")
            val matchWinner: String,
            @SerialName("teams")
            val teams: Teams,
            @SerialName("under_over")
            val underOver: Any,
            @SerialName("winning_percent")
            val winningPercent: WinningPercent
        ) {
            @Serializable
            data class Comparison(
                    @SerialName("att")
                val att: Att,
                    @SerialName("def")
                val def: Def,
                    @SerialName("fish_law")
                val fishLaw: FishLaw,
                    @SerialName("forme")
                val forme: Forme,
                    @SerialName("goals_h2h")
                val goals_h2h: GoalsH2h,
                    @SerialName("h2h")
                val h2h: H2h
            ) {
                @Serializable
                data class Att(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class Def(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class FishLaw(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class Forme(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class GoalsH2h(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )

                @Serializable
                data class H2h(
                    @SerialName("away")
                    val away: String,
                    @SerialName("home")
                    val home: String
                )
            }

            @Serializable
            data class H2h(
                @SerialName("awayTeam")
                val awayTeam: AwayTeam,
                @SerialName("elapsed")
                val elapsed: Int,
                @SerialName("event_date")
                val eventDate: String,
                @SerialName("event_timestamp")
                val eventTimestamp: Int,
                @SerialName("firstHalfStart")
                val firstHalfStart: Int,
                @SerialName("fixture_id")
                val fixtureId: Int,
                @SerialName("goalsAwayTeam")
                val goalsAwayTeam: Int,
                @SerialName("goalsHomeTeam")
                val goalsHomeTeam: Int,
                @SerialName("homeTeam")
                val homeTeam: HomeTeam,
                @SerialName("league")
                val league: League,
                @SerialName("league_id")
                val leagueId: Int,
                @SerialName("referee")
                val referee: String,
                @SerialName("round")
                val round: String,
                @SerialName("score")
                val score: Score,
                @SerialName("secondHalfStart")
                val secondHalfStart: Int,
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
                    val teamId: Int,
                    @SerialName("team_name")
                    val teamName: String
                )

                @Serializable
                data class HomeTeam(
                    @SerialName("logo")
                    val logo: String,
                    @SerialName("team_id")
                    val teamId: Int,
                    @SerialName("team_name")
                    val teamName: String
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
            }

            @Serializable
            data class Teams(
                @SerialName("away")
                val away: Away,
                @SerialName("home")
                val home: Home
            ) {
                @Serializable
                data class Away(
                    @SerialName("all_last_matches")
                    val allLastMatches: AllLastMatches,
                    @SerialName("last_5_matches")
                    val last5Matches: Last5Matches,
                    @SerialName("last_h2h")
                    val lastH2h: LastH2h,
                    @SerialName("team_id")
                    val teamId: Int,
                    @SerialName("team_name")
                    val teamName: String
                ) {
                    @Serializable
                    data class AllLastMatches(
                        @SerialName("goals")
                        val goals: Goals,
                        @SerialName("goalsAvg")
                        val goalsAvg: GoalsAvg,
                        @SerialName("matchs")
                        val matchs: Matchs
                    ) {
                        @Serializable
                        data class Goals(
                            @SerialName("goalsAgainst")
                            val goalsAgainst: GoalsAgainst,
                            @SerialName("goalsFor")
                            val goalsFor: GoalsFor
                        ) {
                            @Serializable
                            data class GoalsAgainst(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class GoalsFor(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )
                        }

                        @Serializable
                        data class GoalsAvg(
                            @SerialName("goalsAgainst")
                            val goalsAgainst: GoalsAgainst,
                            @SerialName("goalsFor")
                            val goalsFor: GoalsFor
                        ) {
                            @Serializable
                            data class GoalsAgainst(
                                @SerialName("away")
                                val away: String,
                                @SerialName("home")
                                val home: String,
                                @SerialName("total")
                                val total: String
                            )

                            @Serializable
                            data class GoalsFor(
                                @SerialName("away")
                                val away: String,
                                @SerialName("home")
                                val home: String,
                                @SerialName("total")
                                val total: String
                            )
                        }

                        @Serializable
                        data class Matchs(
                            @SerialName("draws")
                            val draws: Draws,
                            @SerialName("loses")
                            val loses: Loses,
                            @SerialName("matchsPlayed")
                            val matchsPlayed: MatchsPlayed,
                            @SerialName("wins")
                            val wins: Wins
                        ) {
                            @Serializable
                            data class Draws(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class Loses(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class MatchsPlayed(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class Wins(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )
                        }
                    }

                    @Serializable
                    data class Last5Matches(
                        @SerialName("att")
                        val att: String,
                        @SerialName("def")
                        val def: String,
                        @SerialName("forme")
                        val forme: String,
                        @SerialName("goals")
                        val goals: Int,
                        @SerialName("goals_against")
                        val goalsAgainst: Int,
                        @SerialName("goals_against_avg")
                        val goalsAgainstAvg: Double,
                        @SerialName("goals_avg")
                        val goalsAvg: Double
                    )

                    @Serializable
                    data class LastH2h(
                        @SerialName("draws")
                        val draws: Draws,
                        @SerialName("loses")
                        val loses: Loses,
                        @SerialName("played")
                        val played: Played,
                        @SerialName("wins")
                        val wins: Wins
                    ) {
                        @Serializable
                        data class Draws(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )

                        @Serializable
                        data class Loses(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )

                        @Serializable
                        data class Played(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )

                        @Serializable
                        data class Wins(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )
                    }
                }

                @Serializable
                data class Home(
                    @SerialName("all_last_matches")
                    val allLastMatches: AllLastMatches,
                    @SerialName("last_5_matches")
                    val last5Matches: Last5Matches,
                    @SerialName("last_h2h")
                    val lastH2h: LastH2h,
                    @SerialName("team_id")
                    val teamId: Int,
                    @SerialName("team_name")
                    val teamName: String
                ) {
                    @Serializable
                    data class AllLastMatches(
                        @SerialName("goals")
                        val goals: Goals,
                        @SerialName("goalsAvg")
                        val goalsAvg: GoalsAvg,
                        @SerialName("matchs")
                        val matchs: Matchs
                    ) {
                        @Serializable
                        data class Goals(
                            @SerialName("goalsAgainst")
                            val goalsAgainst: GoalsAgainst,
                            @SerialName("goalsFor")
                            val goalsFor: GoalsFor
                        ) {
                            @Serializable
                            data class GoalsAgainst(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class GoalsFor(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )
                        }

                        @Serializable
                        data class GoalsAvg(
                            @SerialName("goalsAgainst")
                            val goalsAgainst: GoalsAgainst,
                            @SerialName("goalsFor")
                            val goalsFor: GoalsFor
                        ) {
                            @Serializable
                            data class GoalsAgainst(
                                @SerialName("away")
                                val away: String,
                                @SerialName("home")
                                val home: String,
                                @SerialName("total")
                                val total: String
                            )

                            @Serializable
                            data class GoalsFor(
                                @SerialName("away")
                                val away: String,
                                @SerialName("home")
                                val home: String,
                                @SerialName("total")
                                val total: String
                            )
                        }

                        @Serializable
                        data class Matchs(
                            @SerialName("draws")
                            val draws: Draws,
                            @SerialName("loses")
                            val loses: Loses,
                            @SerialName("matchsPlayed")
                            val matchsPlayed: MatchsPlayed,
                            @SerialName("wins")
                            val wins: Wins
                        ) {
                            @Serializable
                            data class Draws(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class Loses(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class MatchsPlayed(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )

                            @Serializable
                            data class Wins(
                                @SerialName("away")
                                val away: Int,
                                @SerialName("home")
                                val home: Int,
                                @SerialName("total")
                                val total: Int
                            )
                        }
                    }

                    @Serializable
                    data class Last5Matches(
                        @SerialName("att")
                        val att: String,
                        @SerialName("def")
                        val def: String,
                        @SerialName("forme")
                        val forme: String,
                        @SerialName("goals")
                        val goals: Int,
                        @SerialName("goals_against")
                        val goalsAgainst: Int,
                        @SerialName("goals_against_avg")
                        val goalsAgainstAvg: Double,
                        @SerialName("goals_avg")
                        val goalsAvg: Double
                    )

                    @Serializable
                    data class LastH2h(
                        @SerialName("draws")
                        val draws: Draws,
                        @SerialName("loses")
                        val loses: Loses,
                        @SerialName("played")
                        val played: Played,
                        @SerialName("wins")
                        val wins: Wins
                    ) {
                        @Serializable
                        data class Draws(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )

                        @Serializable
                        data class Loses(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )

                        @Serializable
                        data class Played(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )

                        @Serializable
                        data class Wins(
                            @SerialName("away")
                            val away: Int,
                            @SerialName("home")
                            val home: Int,
                            @SerialName("total")
                            val total: Int
                        )
                    }
                }
            }

            @Serializable
            data class WinningPercent(
                @SerialName("away")
                val away: String,
                @SerialName("draws")
                val draws: String,
                @SerialName("home")
                val home: String
            )
        }
    }
}