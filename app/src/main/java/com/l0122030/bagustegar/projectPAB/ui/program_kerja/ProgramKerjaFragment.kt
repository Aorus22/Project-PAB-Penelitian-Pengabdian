package com.l0122030.bagustegar.projectPAB.ui.program_kerja

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.l0122030.bagustegar.projectPAB.databinding.FragmentProgramKerjaBinding
import com.l0122030.bagustegar.projectPAB.databinding.TableRowProgramKerjaBinding

class ProgramKerjaFragment : Fragment() {
    private var _binding: FragmentProgramKerjaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProgramKerjaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramKerjaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProgramKerjaViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is ProgramKerjaState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is ProgramKerjaState.Success -> {
                    // Add data rows
                    state.data.forEachIndexed { index, proker ->
                        val tableRow = TableRowProgramKerjaBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (index + 1).toString()
                        tableRow.tvName.text = proker.name
                        tableRow.tv2022.text = proker.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = proker.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = proker.jumlah2024?.toString() ?: "0"
                        val total = (proker.jumlah2022 ?: 0) + (proker.jumlah2023 ?: 0) + (proker.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }

                    // Add total row
                    val totalRow = TableRowProgramKerjaBinding.inflate(layoutInflater, binding.tableLayout, false)
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
                is ProgramKerjaState.Error -> {
                    Log.e("ProgramKerjaFragment", state.error.localizedMessage.orEmpty(), state.error)
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
