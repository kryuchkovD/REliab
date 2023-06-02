package com.samsung.reliab.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.samsung.reliab.R
import com.samsung.reliab.core.utils.StringUtils
import com.samsung.reliab.databinding.ActivityAuthBinding
import com.samsung.reliab.domain.model.AuthOperation
import com.samsung.reliab.domain.model.Response
import com.samsung.reliab.feature.main.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val viewModel: AuthActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityAuthBinding.inflate(layoutInflater)


        Log.d("AuthActivity", "WTF man?!")
        setContentView(binding.root)

        val isUserSignedOut = viewModel.getAuthState().value
        if (!isUserSignedOut) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        with(binding) {
            tvAuth.apply {
                text = StringUtils.createUnderlinedText(getString(R.string.register_user_text))
                setOnClickListener { viewModel.changeUserState() }
            }

            password.editText?.doOnTextChanged { _, _, _, _ ->
                StringUtils.checkPassword(password)
            }

            btnAuth.setOnClickListener {
                val email = email.editText?.text.toString()
                val password = password.editText?.text.toString()
                val operation =
                    if (viewModel.loginUser.value) AuthOperation.SIGN_IN else AuthOperation.SIGN_UP

                if (StringUtils.checkPassword(binding.password)) {
                    viewModel.performAuthOperation(email, password, operation)
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.authResponse.collect {
                    when (it) {
                        is Response.Loading -> Snackbar.make(
                            binding.root,
                            "Loading..",
                            Snackbar.LENGTH_SHORT
                        ).show()

                        is Response.Success -> if (it.data) {
                            startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                            finish()
                        }

                        is Response.Failure -> it.apply {
                            Snackbar.make(
                                binding.root,
                                e.message!!,
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        else -> Unit
                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginUser.collect {
                    with(binding) {
                        if (it) {
                            tvAuthHeader.text = getString(R.string.login_user)
                            btnAuth.text = getString(R.string.login_user)
                            tvAuth.text =
                                StringUtils.createUnderlinedText(getString(R.string.register_user_text))
                        } else {
                            tvAuthHeader.text = getString(R.string.register_user)
                            btnAuth.text = getString(R.string.register_user)
                            tvAuth.text =
                                StringUtils.createUnderlinedText(getString(R.string.login_user_text))
                        }
                    }
                }
            }
        }
    }
}