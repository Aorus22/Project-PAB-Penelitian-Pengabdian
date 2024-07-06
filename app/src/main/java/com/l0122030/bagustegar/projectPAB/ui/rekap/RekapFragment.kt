package com.l0122030.bagustegar.projectPAB.ui.rekap

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
                    state.data.forEachIndexed { index, rekap ->
                        val tableRowBinding = TableRowRekapBinding.inflate(layoutInflater, null, false)
                        val tableRow = tableRowBinding.root

                        tableRowBinding.tvNo.text = (index + 1).toString()
                        tableRowBinding.tvName.text = rekap.name
                        tableRowBinding.tv2022.text = rekap.jumlah2022?.toString() ?: "0"
                        tableRowBinding.tv2023.text = rekap.jumlah2023?.toString() ?: "0"
                        tableRowBinding.tv2024.text = rekap.jumlah2024?.toString() ?: "0"

                        val total = (rekap.jumlah2022 ?: 0) + (rekap.jumlah2023 ?: 0) + (rekap.jumlah2024 ?: 0)
                        tableRowBinding.tvTotal.text = total.toString()

                        binding.tableLayout.addView(tableRow)
                    }
                }
                is RekapState.Error -> {
                    Log.e("RekapFragment", state.error.localizedMessage.orEmpty(), state.error)
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