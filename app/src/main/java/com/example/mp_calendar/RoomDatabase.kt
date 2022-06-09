package com.example.mp_calendar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Schedule::class),version=1, exportSchema = false)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun dao():DAO

    companion object{
        @Volatile
        private var instance: RoomDatabase? = null
        fun getDatabase(context: Context): RoomDatabase{
            return instance?: synchronized(this){
                instance = Room.databaseBuilder(
                    context,
                    RoomDatabase::class.java,
                    "schedule_db"
                ).build()
                instance!!
            }
        }
    }
}