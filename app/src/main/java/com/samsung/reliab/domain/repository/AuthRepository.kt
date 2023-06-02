package com.samsung.reliab.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.samsung.reliab.domain.model.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = Response<Boolean>
typealias SendEmailVerificationResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias ReloadUserResponse = Response<Boolean>
typealias SendPasswordResetEmailResponse = Response<Boolean>
typealias RevokeAccessResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>

interface AuthRepository {
    val currentUser: FirebaseUser?

    suspend fun firebaseSignUpWithEmailAndPassword(
        email: String,
        password: String
    ): SignUpResponse

    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): SignInResponse

    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse

    fun signOut()
}