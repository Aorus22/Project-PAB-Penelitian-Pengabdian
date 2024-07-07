package com.l0122030.bagustegar.projectPAB.ui.haki

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.l0122030.bagustegar.projectPAB.databinding.FragmentHakiBinding
import com.l0122030.bagustegar.projectPAB.databinding.TableRowHakiBinding

class HakiFragment : Fragment() {
    private var _binding: FragmentHakiBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HakiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHakiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HakiViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is HakiState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is HakiState.Success -> {
                    // Add data rows
                    state.data.forEachIndexed { index, haki ->
                        val tableRow = TableRowHakiBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (index + 1).toString()
                        tableRow.tvName.text = haki.name
                        tableRow.tv2022.text = haki.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = haki.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = haki.jumlah2024?.toString() ?: "0"
                        val total = (haki.jumlah2022 ?: 0) + (haki.jumlah2023 ?: 0) + (haki.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }

                    // Add total row
                    val totalRow = TableRowHakiBinding.inflate(layoutInflater, binding.tableLayout, false)
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
                is HakiState.Error -> {
                    Log.e("HakiFragment", state.error.localizedMessage.orEmpty(), state.error)
                    Toast.makeText(requireContext(), state.error.localizedMessage.orEmpty(), Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
