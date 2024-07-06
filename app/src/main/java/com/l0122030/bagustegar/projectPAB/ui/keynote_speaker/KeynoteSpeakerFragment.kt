package com.l0122030.bagustegar.projectPAB.ui.keynote_speaker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.l0122030.bagustegar.projectPAB.databinding.FragmentKeynoteSpeakerBinding
import com.l0122030.bagustegar.projectPAB.databinding.TableRowKeynoteSpeakerBinding


class KeynoteSpeakerFragment : Fragment() {
    private var _binding: FragmentKeynoteSpeakerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: KeynoteSpeakerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKeynoteSpeakerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[KeynoteSpeakerViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is KeynoteSpeakerState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is KeynoteSpeakerState.Success -> {
                    // Add data rows
                    state.data.forEachIndexed { index, keynotespeaker ->
                        val tableRow = TableRowKeynoteSpeakerBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (index + 1).toString()
                        tableRow.tvName.text = keynotespeaker.name
                        tableRow.tv2022.text = keynotespeaker.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = keynotespeaker.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = keynotespeaker.jumlah2024?.toString() ?: "0"
                        val total = (keynotespeaker.jumlah2022 ?: 0) + (keynotespeaker.jumlah2023 ?: 0) + (keynotespeaker.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }

                    // Add total row
                    val totalRow = TableRowKeynoteSpeakerBinding.inflate(layoutInflater, binding.tableLayout, false)
                    totalRow.tvNo.text = ""
                    totalRow.tvName.text = "Total"
                    val totalJumlah2022 = state.data.sumOf { it.jumlah2022 ?: 0 }
                    val totalJumlah2023 = state.data.sumOf { it.jumlah2023 ?: 0 }
                    val totalJumlah2024 = state.data.sumOf { it.jumlah2024 ?: 0 }
                    val totalOfTotals = totalJumlah2022 + totalJumlah2023 + totalJumlah2024
                    totalRow.tv2022.text = totalJumlah2022.toString()
                    totalRow.tv2023.text = totalJumlah2023.toString()
                    totalRow.tv2024.text = totalJumlah2024.toString()
                    totalRow.tvTotal.text = totalOfTotals.toString()
                    binding.tableLayout.addView(totalRow.root)
                }
                is KeynoteSpeakerState.Error -> {
                    Log.e("KeynoteSpeakerFragment", state.error.localizedMessage.orEmpty(), state.error)
                    Toast.makeText(requireContext(), state.error.localizedMessage.orEmpty(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
