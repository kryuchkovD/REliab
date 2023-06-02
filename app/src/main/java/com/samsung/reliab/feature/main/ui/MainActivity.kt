package com.samsung.reliab.feature.main.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.samsung.reliab.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        Log.d("MainActivity", "Shit...")

        setContentView(binding.root)

        with(binding) {
            account.textUserInitials.text = "TU"
            account.textUserName.text = "Test User"
            account.textUserEmail.text = viewModel.currentUser?.email ?: "test@test.com"

            imgBtnLogOut.setOnClickListener { viewModel.signOut() }
        }
    }
}