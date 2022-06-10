package com.example.mp_calendar

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.mp_calendar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            supportFragmentManager.beginTransaction().add(fl.id, Example5Fragment()).commit()

            bn.setOnNavigationItemSelectedListener {
                replaceFragment(
                    when (it.itemId) {
                        R.id.menu_home -> Example5Fragment()
                        R.id.menu_favorites -> MapFragment()
                        else -> SettingsFragment()
                    }
                )
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fl.id, fragment).commit()
    }
}