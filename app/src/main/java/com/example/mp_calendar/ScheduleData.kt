package com.example.mp_calendar

import java.io.Serializable

data class ScheduleData(val name: String, val time:String, val location:String):Serializable
//time은 LocalDateTime.parse(12:30:00) 이용해서 LocalDateTime 타입으로 12시 30분 변환