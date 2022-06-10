package com.example.mp_calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

//테스트용입니다.
@RequiresApi(Build.VERSION_CODES.O)
fun generatescheduels(): List<Schedule> {
    val list = mutableListOf<Schedule>()
    val currentMonth = YearMonth.now()

    val currentMonth17 = currentMonth.atDay(17)
    list.add(Schedule(currentMonth17, stringtotime("7:00"),"todo1", "a"))
    list.add(Schedule(currentMonth17, stringtotime("12:30"), "todo2","b"))

    val currentMonth22 = currentMonth.atDay(22)
    list.add(Schedule(currentMonth22, stringtotime("7:18"),"todo1", "a"))
    list.add(Schedule(currentMonth22, stringtotime("18:22"), "todo2","b"))

    return list
}
//분은 항상 두 자리여야 합니다.
@RequiresApi(Build.VERSION_CODES.O)
fun stringtotime(str:String):LocalTime{
    val timeFormatter=DateTimeFormatter.ofPattern("H:mm")
    val time=LocalTime.parse(str,timeFormatter)
    return time
}
@RequiresApi(Build.VERSION_CODES.O)
fun mklocaldatetime(t: LocalTime, d: LocalDate):LocalDateTime{
    val str="$d $t"
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    val value=LocalDateTime.parse(str,formatter)
    return value
}



