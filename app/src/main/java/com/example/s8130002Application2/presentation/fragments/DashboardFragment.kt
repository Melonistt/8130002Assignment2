package com.example.s8130002Application2.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.s8130002Application2.databinding.FragmentDashboardBinding
import com.example.s8130002Application2.adapters.EntityAdapter
import com.example.s8130002Application2.presentation.viewmodel.DashboardUiState
import com.example.s8130002Application2.presentation.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private val args: DashboardFragmentArgs by navArgs()

    private lateinit var entityAdapter: EntityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        viewModel.fetchDashboard(args.keypass)
    }

    private fun setupRecyclerView() {
        entityAdapter = EntityAdapter { entity ->
            viewModel.selectEntity(entity)
            val action = DashboardFragmentDirections.actionDashboardToDetails(
                property1 = entity.property1,
                property2 = entity.property2,
                description = entity.description
            )
            findNavController().navigate(action)
        }
        binding.entitiesRecyclerView.adapter = entityAdapter
    }

    private fun setupObservers() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DashboardUiState.Idle -> {
                    binding.progressBar.visibility = View.GONE
                }
                is DashboardUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.entitiesRecyclerView.visibility = View.GONE
                }
                is DashboardUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.entitiesRecyclerView.visibility = View.VISIBLE
                    binding.entityCountText.text = "Total Entities: ${state.dashboardResponse.entityTotal}"
                    entityAdapter.submitList(state.dashboardResponse.entities)
                }
                is DashboardUiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.entitiesRecyclerView.visibility = View.GONE
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
