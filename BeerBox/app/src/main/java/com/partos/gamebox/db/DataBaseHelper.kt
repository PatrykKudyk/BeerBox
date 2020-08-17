package com.partos.flashback.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.partos.gamebox.models.*

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
    const val TABLE_NAME_ACTION = "action"
    const val TABLE_COLUMN_ACTION_ROUND = "round"
    const val TABLE_COLUMN_ACTION_NAME = "name"
    const val TABLE_COLUMN_ACTION_ACTION = "action"
    const val TABLE_NAME_CAULDRON = "cauldron"
    const val TABLE_COLUMN_CAULDRON_NAME = "name"
    const val TABLE_NAME_ALCOHOL = "alcohol"
    const val TABLE_COLUMN_ALCOHOL_CAULDRON_ID = "cauldronId"
    const val TABLE_COLUMN_ALCOHOL_NAME = "name"
    const val TABLE_COLUMN_ALCOHOL_AMOUNT = "amount"
    const val TABLE_COLUMN_ALCOHOL_TYPE = "type"

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

    const val SQL_CREATE_TABLE_ACTION =
        "CREATE TABLE ${TableInfo.TABLE_NAME_ACTION} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_ACTION_ROUND} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_ACTION_NAME} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_ACTION_ACTION} TEXT NOT NULL)"

    const val SQL_CREATE_TABLE_CAULDRON_NAME =
        "CREATE TABLE ${TableInfo.TABLE_NAME_CAULDRON} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_CAULDRON_NAME} TEXT NOT NULL)"

    const val SQL_CREATE_TABLE_ALCOHOL =
        "CREATE TABLE ${TableInfo.TABLE_NAME_ALCOHOL} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_ALCOHOL_CAULDRON_ID} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_ALCOHOL_NAME} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_ALCOHOL_AMOUNT} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_ALCOHOL_TYPE} TEXT NOT NULL)"


    const val SQL_DELETE_TABLE_PACKAGES = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_TEAMS}"
    const val SQL_DELETE_TABLE_PLAYERS = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_PLAYERS}"
    const val SQL_DELETE_TABLE_MAFIA = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_MAFIA}"
    const val SQL_DELETE_TABLE_ACTION = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_ACTION}"
    const val SQL_DELETE_TABLE_CAULDRON_NAME = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_CAULDRON}"
    const val SQL_DELETE_TABLE_CAULDRON_ALCOHOL = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_ALCOHOL}"
}

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, TableInfo.DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_TEAMS)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_PLAYERS)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_MAFIA)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_ACTION)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_CAULDRON_NAME)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_ALCOHOL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_PACKAGES)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_PLAYERS)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_MAFIA)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_ACTION)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_CAULDRON_NAME)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_CAULDRON_ALCOHOL)
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

    fun getActionList(): ArrayList<Action> {
        var actionList = ArrayList<Action>()
        val db = readableDatabase
        val selectQuery = "Select * from " + TableInfo.TABLE_NAME_ACTION
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var action =
                    Action(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                        result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_ACTION_ROUND)),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_ACTION_NAME)),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_ACTION_ACTION))
                        )
                actionList.add(action)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return actionList
    }

    fun addAction(action: Action) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_ACTION_ROUND, action.round)
        values.put(TableInfo.TABLE_COLUMN_ACTION_NAME, action.name)
        values.put(TableInfo.TABLE_COLUMN_ACTION_ACTION, action.action)
        db.insert(TableInfo.TABLE_NAME_ACTION, null, values)
        db.close()
    }

    fun updateAction(action: Action) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_ACTION_ROUND, action.round)
        values.put(TableInfo.TABLE_COLUMN_ACTION_NAME, action.name)
        values.put(TableInfo.TABLE_COLUMN_ACTION_ACTION, action.action)
        db.update(
            TableInfo.TABLE_NAME_ACTION, values, BaseColumns._ID + "=?",
            arrayOf(action.id.toString())
        )
        db.close()
    }

    fun deleteAction(action: Action): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_ACTION,
                BaseColumns._ID + "=?",
                arrayOf(action.id.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun getCauldronList(): ArrayList<Cauldron> {
        var cauldronList = ArrayList<Cauldron>()
        val db = readableDatabase
        val selectQuery = "Select * from " + TableInfo.TABLE_NAME_CAULDRON
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var cauldron =
                    Cauldron(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_CAULDRON_NAME))
                    )
                cauldronList.add(cauldron)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return cauldronList
    }

    fun getCauldron(name: String): Cauldron {
        var cauldronList = ArrayList<Cauldron>()
        val db = readableDatabase
        val selectQuery = "Select * from " + TableInfo.TABLE_NAME_CAULDRON + " where " +
        TableInfo.TABLE_COLUMN_CAULDRON_NAME + " = \"" + name + "\""
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var cauldron =
                    Cauldron(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_CAULDRON_NAME))
                    )
                cauldronList.add(cauldron)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        if (cauldronList.size != 0) {
            return cauldronList[0]
        } else {
            cauldronList.add(Cauldron(-1, "test"))
            return cauldronList[0]
        }
    }

    fun getCauldron(cauldronId: Long): Cauldron {
        var cauldronList = ArrayList<Cauldron>()
        val db = readableDatabase
        val selectQuery = "Select * from " + TableInfo.TABLE_NAME_CAULDRON + " where " +
                BaseColumns._ID + " = " + cauldronId.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var cauldron =
                    Cauldron(
                        result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_CAULDRON_NAME))
                    )
                cauldronList.add(cauldron)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
       return cauldronList[0]
    }

    fun addCauldron(cauldron: Cauldron) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_CAULDRON_NAME, cauldron.name)
        db.insert(TableInfo.TABLE_NAME_CAULDRON, null, values)
        db.close()
    }

    fun updateCauldron(cauldron: Cauldron) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_CAULDRON_NAME, cauldron.name)
        db.update(
            TableInfo.TABLE_NAME_CAULDRON, values, BaseColumns._ID + "=?",
            arrayOf(cauldron.id.toString())
        )
        db.close()
    }

    fun deleteCauldron(cauldron: Cauldron): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_CAULDRON,
                BaseColumns._ID + "=?",
                arrayOf(cauldron.id.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun getAlcoholList(cauldronId: Long): ArrayList<AlcoholDb> {
        var alcoholList = ArrayList<AlcoholDb>()
        val db = readableDatabase
        val selectQuery = "Select * from " + TableInfo.TABLE_NAME_ALCOHOL + " where " +
                TableInfo.TABLE_COLUMN_ALCOHOL_CAULDRON_ID + " = " + cauldronId.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var alcohol =
                    AlcoholDb(
                        result.getLong(result.getColumnIndex(BaseColumns._ID)),
                        result.getLong(result.getColumnIndex(TableInfo.TABLE_COLUMN_ALCOHOL_CAULDRON_ID)),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_ALCOHOL_NAME)),
                        result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_ALCOHOL_AMOUNT)),
                        result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_ALCOHOL_TYPE))
                    )
                alcoholList.add(alcohol)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return alcoholList
    }

    fun addAlcohol(alcohol: AlcoholDb) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_CAULDRON_ID, alcohol.cauldronId)
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_NAME, alcohol.name)
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_AMOUNT, alcohol.amount)
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_TYPE, alcohol.type)
        db.insert(TableInfo.TABLE_NAME_ALCOHOL, null, values)
        db.close()
    }

    fun updateAlcohol(alcohol: AlcoholDb) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_CAULDRON_ID, alcohol.cauldronId)
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_NAME, alcohol.name)
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_AMOUNT, alcohol.amount)
        values.put(TableInfo.TABLE_COLUMN_ALCOHOL_TYPE, alcohol.type)
        db.update(
            TableInfo.TABLE_NAME_ALCOHOL, values, BaseColumns._ID + "=?",
            arrayOf(alcohol.id.toString())
        )
        db.close()
    }

    fun deleteAlcohol(alcohol: AlcoholDb): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_ALCOHOL,
                BaseColumns._ID + "=?",
                arrayOf(alcohol.id.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}