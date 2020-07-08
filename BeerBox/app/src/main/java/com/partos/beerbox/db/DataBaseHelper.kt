package com.partos.flashback.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.partos.beerbox.models.Team

object TableInfo : BaseColumns {
    const val DATABASE_NAME = "BeerBox"
    const val TABLE_NAME_TEAMS = "Teams"
    const val TABLE_COLUMN_TEAM_NAME = "name"


}

object BasicCommand {
    const val SQL_CREATE_TABLE_TEAMS =
        "CREATE TABLE ${TableInfo.TABLE_NAME_TEAMS} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_TEAM_NAME} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE_PACKAGES = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_TEAMS}"
}

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, TableInfo.DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_TEAMS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_TEAMS)
        onCreate(db)
    }


    fun getTeamsList(): ArrayList<Team> {
        var teamsList = ArrayList<Team>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_TEAMS}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var myTeam = Team(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_TEAM_NAME))
                )
                teamsList.add(myTeam)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return teamsList
    }

    fun addTeam(name: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_TEAM_NAME, name)
        db.insert(TableInfo.TABLE_NAME_TEAMS, null, values)
        db.close()
    }

    fun updateTeam(team: Team) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_TEAM_NAME, team.name)
        db.update(
            TableInfo.TABLE_NAME_TEAMS, values, BaseColumns._ID + "=?",
            arrayOf(team.id.toString())
        )
        db.close()
    }

    fun deleteTeam(teamId: Long): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_TEAMS,
                BaseColumns._ID + "=?",
                arrayOf(teamId.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}