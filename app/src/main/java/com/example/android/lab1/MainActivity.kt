package com.example.android.lab1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.lab1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run{
            goToNext.setOnClickListener {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(intent)
            }
            openWeb.setOnClickListener {
                val url = resources.getString(R.string.google_web_page)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
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
        _binding = null
    }
    companion object{
        private const val LOG_TAG = "MainActivity"
    }
}