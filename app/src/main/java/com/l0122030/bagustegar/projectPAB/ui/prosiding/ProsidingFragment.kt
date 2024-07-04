package com.l0122030.bagustegar.projectPAB.ui.prosiding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.l0122030.bagustegar.projectPAB.data.model.Prosiding
import com.l0122030.bagustegar.projectPAB.databinding.FragmentProsidingBinding

import com.l0122030.bagustegar.projectPAB.databinding.TableRowProsidingBinding

class ProsidingFragment : Fragment() {
    private var _binding: FragmentProsidingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProsidingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProsidingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ProsidingViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is ProsidingState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is ProsidingState.Success -> {
                    state.data.forEach { prosiding ->
                        val tableRow = TableRowProsidingBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (binding.tableLayout.childCount+1).toString()
                        tableRow.tvName.text = prosiding.name
                        tableRow.tv2022.text = prosiding.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = prosiding.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = prosiding.jumlah2024?.toString() ?: "0"
                        val total = (prosiding.jumlah2022 ?: 0) + (prosiding.jumlah2023 ?: 0) + (prosiding.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }
                }
                is ProsidingState.Error -> {
                    Log.e("ProsidingFragment", state.error.localizedMessage.orEmpty(), state.error)
                    Toast.makeText(requireContext(), state.error.localizedMessage.orEmpty(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


        // Add total row
//        val totalNameTextView = TextView(context).apply {
//            text = "Total"
//        }
//        val totalJumlah2022 = rekapList.sumOf { it.jumlah2022 ?: 0 }
//        val totalJumlah2023 = rekapList.sumOf { it.jumlah2023 ?: 0 }
//        val totalJumlah2024 = rekapList.sumOf { it.jumlah2024 ?: 0 }
//        val totalOfTotals = totalJumlah2022 + totalJumlah2023 + totalJumlah2024
//
//        val totalJumlah2022TextView = TextView(context).apply {
//            text = totalJumlah2022.toString()
//        }
//        val totalJumlah2023TextView = TextView(context).apply {
//            text = totalJumlah2023.toString()
//        }
//        val totalJumlah2024TextView = TextView(context).apply {
//            text = totalJumlah2024.toString()
//        }
//        val totalOfTotalsTextView = TextView(context).apply {
//            text = totalOfTotals.toString()
//        }
//
//        totalRow.addView(totalNameTextView)
//        totalRow.addView(totalJumlah2022TextView)
//        totalRow.addView(totalJumlah2023TextView)
//        totalRow.addView(totalJumlah2024TextView)
//        totalRow.addView(totalOfTotalsTextView)
//
//        tableLayout.addView(totalRow)


}
