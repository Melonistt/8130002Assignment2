package com.example.s8130002Application2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.s8130002Application2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startLearningBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        // Set initial state
        binding.tabLogin.isSelected = true
        binding.tabSignup.isSelected = false
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners() {
        // Tab switching
        binding.tabLogin.setOnClickListener {
            binding.tabLogin.isSelected = true
            binding.tabSignup.isSelected = false
            binding.tabIndicator.animate().translationX(0f).duration = 300
        }

        binding.tabSignup.setOnClickListener {
            binding.tabLogin.isSelected = false
            binding.tabSignup.isSelected = true
            binding.tabIndicator.animate().translationX(
                binding.tabSignup.left.toFloat()
            ).duration = 300
        }

        // Password visibility toggle
        binding.passwordInput.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = 2 // Index of end drawable
                if (event.rawX >= binding.passwordInput.right - binding.passwordInput.compoundDrawables[drawableEnd].bounds.width()) {
                    togglePasswordVisibility()
                    return@setOnTouchListener true
                }
            }
            false
        }

        // Start Learning button
        binding.startLearningBtn.setOnClickListener {
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()

            if (validateInputs(email, password)) {
                // TODO: Implement login logic
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // Forgot password link
        binding.forgotPasswordLink.setOnClickListener {
            // TODO: Navigate to forgot password screen
        }

        // Google login
        binding.googleLoginBtn.setOnClickListener {
            // TODO: Implement Google sign-in
        }

        // Facebook login
        binding.facebookLoginBtn.setOnClickListener {
            // TODO: Implement Facebook sign-in
        }

        // Join free link
        binding.joinFreeLink.setOnClickListener {
            binding.tabSignup.performClick()
        }
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        if (isPasswordVisible) {
            binding.passwordInput.inputType = InputType.TYPE_CLASS_TEXT
        } else {
            binding.passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.passwordInput.setSelection(binding.passwordInput.text.length)
    }

    private fun validateInputs(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.emailInput.error = "Email is required"
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.error = "Enter a valid email"
            return false
        }
        if (password.isEmpty()) {
            binding.passwordInput.error = "Password is required"
            return false
        }
        if (password.length < 6) {
            binding.passwordInput.error = "Password must be at least 6 characters"
            return false
        }
        return true
    }
}
