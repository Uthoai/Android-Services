package com.services

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.services.databinding.ActivityMainBinding
import com.services.service.LoggerService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            btnStartService.setOnClickListener {
                startService(Intent(this@MainActivity, LoggerService::class.java))
            }
            btnStopService.setOnClickListener {
                stopService(Intent(this@MainActivity, LoggerService::class.java))
            }
        }

    }
}

