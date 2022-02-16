package com.example.android.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
    override fun onStart() {
        super.onStart()
        Log.e(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LOG_TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(LOG_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(LOG_TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(LOG_TAG, "onRestart")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG_TAG, "onDestroy")
    }
    companion object{
        private const val LOG_TAG = "DETAIL_ACTIVITY"
    }
}