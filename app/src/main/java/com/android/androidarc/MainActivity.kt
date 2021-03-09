package com.android.androidarc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.live.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment.newInstance())
            .commit()
    }
}