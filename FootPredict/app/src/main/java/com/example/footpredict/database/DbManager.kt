package com.example.footpredict.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.footpredict.database.SavedMatchesDatabase.COLUMN_NAME_AWAY_TEAM_NAME
import com.example.footpredict.database.SavedMatchesDatabase.COLUMN_NAME_AWAY_TEAM_SCORE
import com.example.footpredict.database.SavedMatchesDatabase.COLUMN_NAME_HOME_TEAM_NAME
import com.example.footpredict.database.SavedMatchesDatabase.COLUMN_NAME_HOME_TEAM_SCORE
import com.example.footpredict.database.SavedMatchesDatabase.SQL_DELETE_ENTRIES
import com.example.footpredict.models.Simulation

class DbManager(context: Context) {
    val dbHelper : DbHelper = DbHelper(context)
    var db : SQLiteDatabase? = null

    fun openDb(){
        db = dbHelper.writableDatabase
    }

    fun closeDb(){
        dbHelper.close()
    }

    fun deleteDb(){
        db?.execSQL(SQL_DELETE_ENTRIES)
    }

    fun insertToDatabase(match : Simulation) {
        val values = ContentValues().apply {
            put(COLUMN_NAME_HOME_TEAM_NAME, match.firstTeamName)
            put(COLUMN_NAME_AWAY_TEAM_NAME, match.secondTeamName)
            put(COLUMN_NAME_HOME_TEAM_SCORE, match.firstTeamScore)
            put(COLUMN_NAME_AWAY_TEAM_SCORE, match.secondTeamScore)
        }
        db?.insert(SavedMatchesDatabase.TABLE_NAME, null, values)
    }

    fun executeSql(script : String){
        db?.execSQL(script)
    }


    fun getMatches() : ArrayList<Simulation>{
        val cursor = db?.query(SavedMatchesDatabase.TABLE_NAME,null,null,null,null,null,null)

        val matches = ArrayList<Simulation>()
        with(cursor){
            while(this?.moveToNext()!!){
                val match = Simulation(
                        getString(getColumnIndex(COLUMN_NAME_HOME_TEAM_NAME)).toString(),
                        getString(getColumnIndex(COLUMN_NAME_AWAY_TEAM_NAME)).toString(),
                        getInt(getColumnIndex(COLUMN_NAME_HOME_TEAM_SCORE)),
                        getInt(getColumnIndex(COLUMN_NAME_AWAY_TEAM_SCORE))
                        );
                matches.add(match)
            }
        }
        cursor?.close()
        return matches;
    }
}