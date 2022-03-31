package com.example.android.catsapp

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.android.catsapp.databinding.ActivityMainBinding
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsApi
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRepository
import com.example.android.catsapp.datalayer.catsbreeedsfeature.CatsRetrofitSource
import com.example.android.catsapp.datalayer.retrofitbuilder.ServiceBuilder
import com.example.android.catsapp.uilayer.catslistfeature.viewmodel.BreedsDetailsViewModelFactory
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), HasDefaultViewModelProviderFactory {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpNavigation()
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

    // make EditText loose focus on tap outside (applies to Activity and Fragments)
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus ?: let {
                return@dispatchTouchEvent super.dispatchTouchEvent(ev)
            }
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.toolbar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.menu.findItem(R.id.favorites).isVisible =
                destination.id == R.id.breeds_list_fragment
        }

        binding.toolbar.menu.findItem(R.id.favorites).setOnMenuItemClickListener {
            navController.navigate(R.id.action_breedsList_to_favoriteBreeds)
            true
        }
    }
}