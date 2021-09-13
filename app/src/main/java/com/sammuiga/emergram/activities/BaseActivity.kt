package com.sammuiga.emergram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sammuiga.emergram.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}