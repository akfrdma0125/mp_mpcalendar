package com.example.mp_calendar

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.YearMonth

//테스트용입니다.
@RequiresApi(Build.VERSION_CODES.O)
fun generateScheduels(): List<Schedule> {
    val list = mutableListOf<Schedule>()
    val currentMonth = YearMonth.now()

    val currentMonth17 = currentMonth.atDay(17)
    list.add(Schedule(currentMonth17,currentMonth17.atTime(14, 0),"todo1", "a",false,false, 50))
    list.add(Schedule(currentMonth17,currentMonth17.atTime(21, 30), "todo2","b", true, true,60))

    val currentMonth22 = currentMonth.atDay(22)
    list.add(Schedule(currentMonth22,currentMonth22.atTime(14, 0),"todo1", "a",false, false,50))
    list.add(Schedule(currentMonth22,currentMonth22.atTime(21, 30), "todo2","b", true, true, 60))

    return list
}
