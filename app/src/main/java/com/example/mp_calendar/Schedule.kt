package com.example.mp_calendar

import androidx.room.Entity
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName= "schedules", primaryKeys = ["description"])
data class Schedule(
    val date: LocalDate,
    val time: LocalDateTime,
    val description: String,
    val location: String="",
    val prevSchedule: Boolean = false,
    val nextSchedule: Boolean = false,
    val travelTime: Int =0
    ):Serializable
