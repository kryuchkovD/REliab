package com.samsung.reliab.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.samsung.reliab.data.repository.AuthRepositoryImpl
import com.samsung.reliab.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class AppModule {

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(Firebase.auth)
}