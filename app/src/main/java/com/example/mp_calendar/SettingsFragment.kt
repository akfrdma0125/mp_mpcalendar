package com.example.mp_calendar

import android.R
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.mp_calendar.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


class SettingsFragment : Fragment() {

    lateinit var binding: FragmentSettingsBinding
    var isAlarmOn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.alarmOnWhite.visibility = View.VISIBLE
        binding.alarmOffPink.visibility = View.VISIBLE
        binding.alarmOnPink.visibility = View.GONE
        binding.alarmOffWhite.visibility = View.GONE

        binding.alarmOnWhite.setOnClickListener {
            if (!isAlarmOn) {//알람이 꺼져있다면
                binding.alarmOnPink.visibility = View.VISIBLE
                binding.alarmOffWhite.visibility = View.VISIBLE
                binding.alarmOnWhite.visibility = View.GONE
                binding.alarmOffPink.visibility = View.GONE
                //알람을 킴
                isAlarmOn = true


                var picked_time = Calendar.getInstance()
                var picked_hour = picked_time.get(Calendar.HOUR)
                var picked_minute = picked_time.get(Calendar.MINUTE)

                var timeListener = object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        binding.alarmTimeText.text = "${hourOfDay}:${minute}"
                    }
                }
                var builder = TimePickerDialog(
                    activity, R.style.Theme_Holo_Light_Dialog_NoActionBar,
                    timeListener, picked_hour, picked_minute, false
                )
                builder.show()

                alarm_time_linearlayout.visibility = View.VISIBLE

            }
        }

        binding.alarmOffWhite.setOnClickListener {
            if (isAlarmOn) {//알람이 켜져있다면
                binding.alarmOnWhite.visibility = View.VISIBLE
                binding.alarmOffPink.visibility = View.VISIBLE
                binding.alarmOnPink.visibility = View.GONE
                binding.alarmOffWhite.visibility = View.GONE
                isAlarmOn = false
                //알람끄기

                alarm_time_linearlayout.visibility = View.INVISIBLE
            }
        }

    }
}