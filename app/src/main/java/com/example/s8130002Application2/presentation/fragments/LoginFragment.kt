package com.example.s8130002Application2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.s8130002Application2.R
import com.example.s8130002Application2.databinding.FragmentLoginBinding
import com.example.s8130002Application2.presentation.viewmodel.LoginUiState
import com.example.s8130002Application2.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    private fun setupListeners() {
        binding.startLearningBtn.setOnClickListener {
            val username = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString().trim()


            viewModel.login(username, password, endpoint = "footscray")
        }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginUiState.Idle -> {
                    binding.startLearningBtn.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                }
                is LoginUiState.Loading -> {
                    binding.startLearningBtn.isEnabled = false
                    binding.progressBar.visibility = View.VISIBLE
                }
                is LoginUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val action = LoginFragmentDirections.actionLoginToDashboard(
                        keypass = state.authResponse.keypass
                    )
                    findNavController().navigate(action)
                }
                is LoginUiState.Error -> {
                    binding.startLearningBtn.isEnabled = true
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
