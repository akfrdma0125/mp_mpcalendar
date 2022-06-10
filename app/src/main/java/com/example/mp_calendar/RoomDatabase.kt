package com.example.mp_calendar

import android.content.Context
import androidx.room.*
import androidx.room.RoomDatabase

@TypeConverters(
  value = [
      Converters::class,timeConverters::class
  ]
)
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