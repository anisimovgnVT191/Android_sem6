package com.example.android.catsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import com.example.android.catsapp.databinding.ActivityMainBinding
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsApi
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRepository
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRetrofitSource
import com.example.android.catsapp.datalayer.retrofitbuilder.ServiceBuilder
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsDetailsViewModelFactory
import com.example.android.catsapp.uilayer.catslistfeature.fragments.BreedsListFragment
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), HasDefaultViewModelProviderFactory {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, BreedsListFragment()).commit()
        }
    }
    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return BreedsDetailsViewModelFactory(
            CatsRepository(
                CatsRetrofitSource(
                    ServiceBuilder.buildService(
                        CatsApi::class.java
                    ), Dispatchers.IO
                )
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}