package com.samsung.reliab.core.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import com.google.android.material.textfield.TextInputLayout
import com.samsung.reliab.R

object StringUtils {
    @JvmStatic
    fun createUnderlinedText(str: String): CharSequence = SpannableString(str).apply {
        setSpan(UnderlineSpan(), 0, str.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    }

    @JvmStatic
    fun checkPassword(textInputLayout: TextInputLayout): Boolean {
        val password = textInputLayout.editText?.text.toString()
        val resources = textInputLayout.context.resources

        // Check password length
        if (password.length < 8) {
            textInputLayout.error = resources.getString(R.string.password_error_length)
            return false
        }

        // Check lowercase char
        if (!password.any { it.isLowerCase() }) {
            textInputLayout.error = resources.getString(R.string.password_error_lowercase)
            return false
        }

        // Check uppercase char
        if (!password.any { it.isUpperCase() }) {
            textInputLayout.error = resources.getString(R.string.password_error_uppercase)
            return false
        }

        // Check digit
        if (!password.any { it.isDigit() }) {
            textInputLayout.error = resources.getString(R.string.password_error_digit)
            return false
        }

        textInputLayout.error = null
        return true
    }
}