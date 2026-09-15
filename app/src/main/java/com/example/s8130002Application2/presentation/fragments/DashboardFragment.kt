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

        viewModel.fetchDashboard("history")
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
            android.util.Log.d("DashboardFragment", "UI State: ${state::class.simpleName}")

            when (state) {
                is DashboardUiState.Loading -> {
                    android.util.Log.d("DashboardFragment", "LOADING...")
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DashboardUiState.Success -> {
                    android.util.Log.d("DashboardFragment", "SUCCESS!")
                    android.util.Log.d("DashboardFragment", "Entity count: ${state.dashboardResponse.entities.size}")
                    android.util.Log.d("DashboardFragment", "Entities: ${state.dashboardResponse.entities}")
                    binding.progressBar.visibility = View.GONE
                    binding.entityCountText.text = "Total Entities: ${state.dashboardResponse.entityTotal}"
                    entityAdapter.submitList(state.dashboardResponse.entities)
                }
                is DashboardUiState.Error -> {
                    android.util.Log.e("DashboardFragment", "ERROR: ${state.message}")
                    binding.progressBar.visibility = View.GONE
                }
                is DashboardUiState.Idle -> {
                    android.util.Log.d("DashboardFragment", "IDLE")
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}


