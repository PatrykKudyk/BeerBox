package com.partos.flashback.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.partos.gamebox.models.Player
import com.partos.gamebox.models.Round
import com.partos.gamebox.models.Team

object TableInfo : BaseColumns {
    const val DATABASE_NAME = "BeerBox"
    const val TABLE_NAME_TEAMS = "Teams"
    const val TABLE_COLUMN_TEAM_NAME = "name"
    const val TABLE_NAME_PLAYERS = "Players"
    const val TABLE_COLUMN_PLAYERS_ROLE = "role"
    const val TABLE_COLUMN_PLAYERS_NAME = "name"
    const val TABLE_COLUMN_PLAYERS_IS_ALIVE = "isAlive"
    const val TABLE_NAME_MAFIA = "mafia"
    const val TABLE_COLUMN_MAFIA = "round"

}

object BasicCommand {
    const val SQL_CREATE_TABLE_TEAMS =
        "CREATE TABLE ${TableInfo.TABLE_NAME_TEAMS} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_TEAM_NAME} TEXT NOT NULL)"

    const val SQL_CREATE_TABLE_PLAYERS =
        "CREATE TABLE ${TableInfo.TABLE_NAME_PLAYERS} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_PLAYERS_ROLE} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_PLAYERS_NAME} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_PLAYERS_IS_ALIVE} INTEGER NOT NULL)"

    const val SQL_CREATE_TABLE_MAFIA =
        "CREATE TABLE ${TableInfo.TABLE_NAME_MAFIA} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_MAFIA} INTEGER NOT NULL)"

    const val SQL_DELETE_TABLE_PACKAGES = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_TEAMS}"
    const val SQL_DELETE_TABLE_PLAYERS = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_PLAYERS}"
    const val SQL_DELETE_TABLE_MAFIA = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_MAFIA}"
}

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, TableInfo.DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_TEAMS)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_PLAYERS)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_MAFIA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_TEAMS)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_PLAYERS)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_MAFIA)
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

    fun getPlayersList(): ArrayList<Player> {
        var playersList = ArrayList<Player>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_PLAYERS}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var myPlayer = Player(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_PLAYERS_ROLE)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_PLAYERS_NAME)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_PLAYERS_IS_ALIVE))
                )
                playersList.add(myPlayer)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return playersList
    }

    fun addPlayer(role: String, name: String, isAlive: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_PLAYERS_ROLE, role)
        values.put(TableInfo.TABLE_COLUMN_PLAYERS_NAME, name)
        values.put(TableInfo.TABLE_COLUMN_PLAYERS_IS_ALIVE, isAlive)
        db.insert(TableInfo.TABLE_NAME_PLAYERS, null, values)
        db.close()
    }

    fun updatePLayer(player: Player) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_PLAYERS_ROLE, player.role)
        values.put(TableInfo.TABLE_COLUMN_PLAYERS_NAME, player.name)
        values.put(TableInfo.TABLE_COLUMN_PLAYERS_IS_ALIVE, player.isAlive)
        db.update(
            TableInfo.TABLE_NAME_PLAYERS, values, BaseColumns._ID + "=?",
            arrayOf(player.id.toString())
        )
        db.close()
    }

    fun deletePlayer(playerId: Long): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_PLAYERS,
                BaseColumns._ID + "=?",
                arrayOf(playerId.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun getMafiaRound(): ArrayList<Round> {
        var roundList = ArrayList<Round>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_MAFIA}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var round =
                    Round(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                        result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_MAFIA))
                    )
                roundList.add(round)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return roundList
    }

    fun addMafiaRound(round: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_MAFIA, round)
        db.insert(TableInfo.TABLE_NAME_MAFIA, null, values)
        db.close()
    }

    fun updateMafiaRound(round: Round) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_MAFIA, round.number)
        db.update(
            TableInfo.TABLE_NAME_MAFIA, values, BaseColumns._ID + "=?",
            arrayOf(round.id.toString())
        )
        db.close()
    }

    fun deleteMafiaRound(round: Round): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_MAFIA,
                BaseColumns._ID + "=?",
                arrayOf(round.id.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}