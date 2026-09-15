package com.example.s8130002Application2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s8130002Application2.adapters.EntityAdapter
import com.example.s8130002Application2.databinding.FragmentDashboardBinding
import com.example.s8130002Application2.presentation.viewmodel.DashboardViewModel
import com.example.s8130002Application2.presentation.viewmodel.DashboardUiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var entityAdapter: EntityAdapter  // Renamed to lowercase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        // Provide the required onItemClick callback
        entityAdapter = EntityAdapter { entity ->
            // Handle item click - e.g., navigate to details, show in dialog, etc.
            viewModel.selectEntity(entity)
        }
        binding.instructorsRecycler.apply {
            adapter = entityAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DashboardUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DashboardUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    // Use submitList() instead of updateList()
                    entityAdapter.submitList(state.dashboardResponse.entities)
                }
                is DashboardUiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    // Handle error - show toast or snackbar
                }
                is DashboardUiState.Idle -> {
                    // Initial state
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}


