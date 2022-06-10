package com.example.mp_calendar

import android.R
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.example.mp_calendar.databinding.ActivityEditBinding
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    lateinit var date: LocalDate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        date=intent.getSerializableExtra("date") as LocalDate
        initLayout()
    }

    private fun initLayout() {

        binding.pickTimeBtn.setOnClickListener {
            var picked_time= Calendar.getInstance()
            var picked_hour=picked_time.get(Calendar.HOUR)
            var picked_minute=picked_time.get(Calendar.MINUTE)

            var timeListener=object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    val str=String.format("%02d:%02d",hourOfDay,minute)
                    binding.timeText.text=str
                }
            }
            var builder= TimePickerDialog(this, R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeListener,picked_hour,picked_minute,false)
            builder.show()
        }

        binding.saveBtn.setOnClickListener {
            val name=binding.nameEt.text.toString()
            val time=binding.timeText.text.toString()
            val place=binding.placeEt.text.toString()
            sendInfo(name,time,place)//title,time,place 를 intent에 넣어서 DayActivity에 전달해야함
        }
    }

    private fun sendInfo(name:String, time:String, place:String) {
        val intent= Intent()

        intent.putExtra("schedule", Schedule(date, stringtotime(time),name,place))
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}