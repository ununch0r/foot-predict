package com.example.footpredict.database

import android.provider.BaseColumns

object SavedMatchesDatabase : BaseColumns {
    const val TABLE_NAME = "Match"
    const val COLUMN_NAME_HOME_TEAM_NAME = "HomeTeamName"
    const val COLUMN_NAME_AWAY_TEAM_NAME = "AwayTeamName"
    const val COLUMN_NAME_HOME_TEAM_SCORE = "HomeTeamScore"
    const val COLUMN_NAME_AWAY_TEAM_SCORE = "AwayTeamScore"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "SavedMatches.db"

    const val SQL_CREATE_TABLE  =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "$COLUMN_NAME_HOME_TEAM_NAME TEXT," +
                    "$COLUMN_NAME_AWAY_TEAM_NAME TEXT," +
                    "$COLUMN_NAME_HOME_TEAM_SCORE INTEGER," +
                    "$COLUMN_NAME_AWAY_TEAM_SCORE INTEGER)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
}