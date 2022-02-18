package com.example.android.lab12

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import com.example.android.lab12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSpannableTitleColor()
        setListeners()
        setViewPagerFragment()
    }
    private fun setSpannableTitleColor(){
        val title = binding.appBar.title.toString()
        val titleSpannable = SpannableString(title)
        val textColorBlack = ForegroundColorSpan(Color.BLACK)
        val textColorYellow = ForegroundColorSpan(resources.getColor(R.color.dark_yellow))
        titleSpannable.apply {
            setSpan(textColorBlack, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(textColorYellow, 5, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        binding.appBar.title = titleSpannable


    }
    private fun setViewPagerFragment(){
        supportFragmentManager.beginTransaction()
            .replace(binding.viewpagerContainer.id, ViewPagerFragment())
            .commit()
    }
    private fun setListeners(){
        binding.appBar.setNavigationOnClickListener{
            Toast.makeText(this, "Settings button pressed", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}