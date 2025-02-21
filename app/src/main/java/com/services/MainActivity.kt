package com.services

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.services.databinding.ActivityMainBinding
import com.services.service.LoggerService

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                100)
        }

        binding.apply {
            btnStartService.setOnClickListener {
                startForegroundService(Intent(this@MainActivity, LoggerService::class.java))
            }
            btnStopService.setOnClickListener {
                stopService(Intent(this@MainActivity, LoggerService::class.java))
            }
        }

    }
}

