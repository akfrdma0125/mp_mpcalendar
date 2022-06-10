package com.example.mp_calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mp_calendar.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val scheduleInfo=intent.getSerializableExtra("scheduleInfo") as Schedule

        binding.infoTitle.text=scheduleInfo.name
        binding.infoTime.text=scheduleInfo.time.toString()
        binding.infoPlace.text=scheduleInfo.location

    }
}
