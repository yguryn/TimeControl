package com.example.timecontrol.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [(ControlDayModel::class)], version = 2, exportSchema = false)
abstract class DayControlDataBase : RoomDatabase() {

    abstract fun dayDao(): DayDao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE controldays ADD COLUMN leaveTime TEXT NOT NULL DEFAULT ''")
            }
        }
    }
}
