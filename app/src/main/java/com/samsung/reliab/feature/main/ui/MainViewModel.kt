package com.samsung.reliab.feature.main.ui

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.samsung.reliab.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {
    val currentUser: FirebaseUser? = repo.currentUser

    fun signOut() = repo.signOut()
}