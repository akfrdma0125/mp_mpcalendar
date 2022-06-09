package com.example.mp_calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.day.ScheduleData
import com.example.mp_calendar.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scheduleInfo=intent.getSerializableExtra("scheduleInfo") as ScheduleData

        binding.infoTitle.text=scheduleInfo.name
        binding.infoTime.text=scheduleInfo.time
        binding.infoPlace.text=scheduleInfo.location


    }
}
