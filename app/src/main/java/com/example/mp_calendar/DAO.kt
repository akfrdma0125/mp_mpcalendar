package com.example.mp_calendar

import androidx.lifecycle.LiveData
import androidx.room.*
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface DAO {
    @Query("select * from schedules")
    suspend fun getAll(): List<Schedule>

    @Query("select * from schedules where date=:date")
    suspend fun getSchedulesbydate(date: LocalDate): List<Schedule>

    @Query("select * from schedules where description=:des")
    suspend fun getSchedulesbydes(des: String): List<Schedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: Schedule)

    @Query("delete from schedules where description=:des")
    suspend fun delete(des:String)

}