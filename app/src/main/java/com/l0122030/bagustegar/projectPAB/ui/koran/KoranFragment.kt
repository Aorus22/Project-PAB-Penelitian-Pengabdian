package com.l0122030.bagustegar.projectPAB.ui.koran

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.l0122030.bagustegar.projectPAB.databinding.FragmentKoranBinding
import com.l0122030.bagustegar.projectPAB.databinding.TableRowKoranBinding

class KoranFragment : Fragment() {
    private var _binding: FragmentKoranBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: KoranViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKoranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[KoranViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is KoranState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is KoranState.Success -> {
                    // Add data rows
                    state.data.forEachIndexed { index, koran ->
                        val tableRow = TableRowKoranBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (index + 1).toString()
                        tableRow.tvName.text = koran.name
                        tableRow.tv2022.text = koran.jumlah2022?.toString() ?: "0"
                        tableRow.tv2023.text = koran.jumlah2023?.toString() ?: "0"
                        tableRow.tv2024.text = koran.jumlah2024?.toString() ?: "0"
                        val total = (koran.jumlah2022 ?: 0) + (koran.jumlah2023 ?: 0) + (koran.jumlah2024 ?: 0)
                        tableRow.tvTotal.text = total.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }
                }
                is KoranState.Error -> {
                    Log.e("KoranFragment", state.error.localizedMessage.orEmpty(), state.error)
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
