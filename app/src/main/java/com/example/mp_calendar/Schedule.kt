package com.example.mp_calendar

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Schedule(
    val date: LocalDate,
    val time: LocalTime,
    val name: String,
    val location: String="",
    val prev: Boolean = false,
    val next: Boolean = false,
    val travelTime: Int =0
    ):Serializable
