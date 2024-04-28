package com.example.coinapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coinapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction()
            .add(binding.mainContainer.id, MainFragment::class.java, null)
            .commit()

    }
}