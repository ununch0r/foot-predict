package com.example.footpredict.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.footpredict.database.SavedMatchesDatabase.DATABASE_NAME
import com.example.footpredict.database.SavedMatchesDatabase.DATABASE_VERSION
import com.example.footpredict.database.SavedMatchesDatabase.SQL_CREATE_TABLE
import com.example.footpredict.database.SavedMatchesDatabase.SQL_DELETE_ENTRIES

class DbHelper(context : Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}