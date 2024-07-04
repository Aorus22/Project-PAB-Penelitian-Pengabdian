package com.l0122030.bagustegar.projectPAB.ui.sumber_biaya

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
import com.l0122030.bagustegar.projectPAB.data.model.SumberBiaya
import com.l0122030.bagustegar.projectPAB.databinding.FragmentSumberBiayaBinding

import com.l0122030.bagustegar.projectPAB.databinding.TableRowSumberBiayaBinding

class SumberBiayaFragment : Fragment() {
    private var _binding: FragmentSumberBiayaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SumberBiayaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSumberBiayaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[SumberBiayaViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is SumberBiayaState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is SumberBiayaState.Success -> {
                    state.data.forEach { sumber ->
                        val tableRow = TableRowSumberBiayaBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (binding.tableLayout.childCount+1).toString()
                        tableRow.tvName.text = sumber.name
                        tableRow.tv2022.text = sumber.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = sumber.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = sumber.jumlah2024?.toString() ?: "0"
                        val total = (sumber.jumlah2022 ?: 0) + (sumber.jumlah2023 ?: 0) + (sumber.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }
                }
                is SumberBiayaState.Error -> {
                    Log.e("SumberBiayaFragment", state.error.localizedMessage.orEmpty(), state.error)
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
