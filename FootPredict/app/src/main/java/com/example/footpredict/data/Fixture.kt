package com.example.footpredict.data

data class Fixture(
    val api: Api
) {
    data class Api(
        val predictions: List<Prediction>,
        val results: Int
    ) {
        data class Prediction(
            val advice: String,
            val comparison: Comparison,
            val goals_away: String,
            val goals_home: String,
            val match_winner: String,
            val teams: Teams,
            val under_over: Any,
            val winning_percent: WinningPercent
        ) {
            data class Comparison(
                val att: Att,
                val def: Def,
                val fish_law: FishLaw,
                val forme: Forme,
                val goals_h2h: GoalsH2h,
                val h2h: H2h
            ) {
                data class Att(
                    val away: String,
                    val home: String
                )

                data class Def(
                    val away: String,
                    val home: String
                )

                data class FishLaw(
                    val away: String,
                    val home: String
                )

                data class Forme(
                    val away: String,
                    val home: String
                )

                data class GoalsH2h(
                    val away: String,
                    val home: String
                )

                data class H2h(
                    val away: String,
                    val home: String
                )
            }

            data class Teams(
                val away: Away,
                val home: Home
            ) {
                data class Away(
                    val last_5_matches: Last5Matches,
                    val last_h2h: LastH2h,
                    val team_id: Int,
                    val team_name: String
                ) {
                    data class AllLastMatches(
                        val goals: Goals,
                        val goalsAvg: GoalsAvg,
                        val matchs: Matchs
                    ) {
                        data class Goals(
                            val goalsAgainst: GoalsAgainst,
                            val goalsFor: GoalsFor
                        ) {
                            data class GoalsAgainst(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class GoalsFor(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )
                        }

                        data class GoalsAvg(
                            val goalsAgainst: GoalsAgainst,
                            val goalsFor: GoalsFor
                        ) {
                            data class GoalsAgainst(
                                val away: String,
                                val home: String,
                                val total: String
                            )

                            data class GoalsFor(
                                val away: String,
                                val home: String,
                                val total: String
                            )
                        }

                        data class Matchs(
                            val draws: Draws,
                            val loses: Loses,
                            val matchsPlayed: MatchsPlayed,
                            val wins: Wins
                        ) {
                            data class Draws(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class Loses(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class MatchsPlayed(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class Wins(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )
                        }
                    }

                    data class Last5Matches(
                        val att: String,
                        val def: String,
                        val forme: String,
                        val goals: Int,
                        val goals_against: Int,
                        val goals_against_avg: Double,
                        val goals_avg: Double
                    )

                    data class LastH2h(
                        val draws: Draws,
                        val loses: Loses,
                        val played: Played,
                        val wins: Wins
                    ) {
                        data class Draws(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )

                        data class Loses(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )

                        data class Played(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )

                        data class Wins(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )
                    }
                }

                data class Home(
                    val all_last_matches: AllLastMatches,
                    val last_5_matches: Last5Matches,
                    val last_h2h: LastH2h,
                    val team_id: Int,
                    val team_name: String
                ) {
                    data class AllLastMatches(
                        val goals: Goals,
                        val goalsAvg: GoalsAvg,
                        val matchs: Matchs
                    ) {
                        data class Goals(
                            val goalsAgainst: GoalsAgainst,
                            val goalsFor: GoalsFor
                        ) {
                            data class GoalsAgainst(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class GoalsFor(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )
                        }

                        data class GoalsAvg(
                            val goalsAgainst: GoalsAgainst,
                            val goalsFor: GoalsFor
                        ) {
                            data class GoalsAgainst(
                                val away: String,
                                val home: String,
                                val total: String
                            )

                            data class GoalsFor(
                                val away: String,
                                val home: String,
                                val total: String
                            )
                        }

                        data class Matchs(
                            val draws: Draws,
                            val loses: Loses,
                            val matchsPlayed: MatchsPlayed,
                            val wins: Wins
                        ) {
                            data class Draws(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class Loses(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class MatchsPlayed(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )

                            data class Wins(
                                val away: Int,
                                val home: Int,
                                val total: Int
                            )
                        }
                    }

                    data class Last5Matches(
                        val att: String,
                        val def: String,
                        val forme: String,
                        val goals: Int,
                        val goals_against: Int,
                        val goals_against_avg: Double,
                        val goals_avg: Double
                    )

                    data class LastH2h(
                        val draws: Draws,
                        val loses: Loses,
                        val played: Played,
                        val wins: Wins
                    ) {
                        data class Draws(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )

                        data class Loses(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )

                        data class Played(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )

                        data class Wins(
                            val away: Int,
                            val home: Int,
                            val total: Int
                        )
                    }
                }
            }

            data class WinningPercent(
                val away: String,
                val draws: String,
                val home: String
            )
        }
    }
}