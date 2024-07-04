package com.l0122030.bagustegar.projectPAB.ui.rekap

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
import com.l0122030.bagustegar.projectPAB.data.model.Rekap
import com.l0122030.bagustegar.projectPAB.databinding.FragmentRekapBinding

import com.l0122030.bagustegar.projectPAB.databinding.TableRowRekapBinding

class RekapFragment : Fragment() {
    private var _binding: FragmentRekapBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RekapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRekapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[RekapViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is RekapState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is RekapState.Success -> {
                    state.data.forEach { rekap ->
                        val tableRow = TableRowRekapBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (binding.tableLayout.childCount+1).toString()
                        tableRow.tvName.text = rekap.name
                        tableRow.tv2022.text = rekap.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = rekap.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = rekap.jumlah2024?.toString() ?: "0"
                        val total = (rekap.jumlah2022 ?: 0) + (rekap.jumlah2023 ?: 0) + (rekap.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }
                }
                is RekapState.Error -> {
                    Log.e("RekapFragment", state.error.localizedMessage.orEmpty(), state.error)
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
