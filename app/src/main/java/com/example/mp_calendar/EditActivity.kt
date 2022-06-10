package com.example.mp_calendar

import android.R
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TimePicker
import com.example.mp_calendar.databinding.ActivityEditBinding
import java.util.*

class EditActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initLayout()
    }

    /*private fun initLayout() {

        binding.pickTimeBtn.setOnClickListener {
            var picked_time= Calendar.getInstance()
            var picked_hour=picked_time.get(Calendar.HOUR)
            var picked_minute=picked_time.get(Calendar.MINUTE)

            var timeListener=object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    binding.timeText.text="${hourOfDay}:${minute}"
                }
            }
            var builder= TimePickerDialog(this, R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeListener,picked_hour,picked_minute,false)
            builder.show()
        }

        binding.saveBtn.setOnClickListener {
            val name=binding.nameEt.text.toString()
            //val time=binding.timeEt.text.toString().toInt()
            val time=binding.timeText.text.toString()
            val place=binding.placeEt.text.toString()
            sendInfo(name,time,place)//title,time,place 를 intent에 넣어서 DayActivity에 전달해야함
        }
    }

    private fun sendInfo(name:String,time:String,place:String) {
        val intent= Intent()
        intent.putExtra("schedule", Schedule(name,time,place))
        setResult(Activity.RESULT_OK,intent)
        finish()
    }*/
}