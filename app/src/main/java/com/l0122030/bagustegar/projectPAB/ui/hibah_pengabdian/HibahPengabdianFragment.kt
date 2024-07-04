package com.l0122030.bagustegar.projectPAB.ui.hibah_pengabdian

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.l0122030.bagustegar.projectPAB.R
import com.l0122030.bagustegar.projectPAB.databinding.FragmentHibahPengabdianBinding
import com.l0122030.bagustegar.projectPAB.databinding.TableRowHibahBinding

class HibahPengabdianFragment : Fragment() {

    private var _binding: FragmentHibahPengabdianBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HibahViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHibahPengabdianBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HibahViewModel::class.java]

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            binding.progressCircular.visibility = View.GONE

            when (state) {
                is HibahState.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is HibahState.Success -> {
                    state.data.forEach { hibah ->
                        val tableRow = TableRowHibahBinding.inflate(layoutInflater, binding.tableLayout, false)
                        tableRow.tvNo.text = (binding.tableLayout.childCount).toString()
                        tableRow.tvFaculty.text = hibah.faculty
                        tableRow.tvValue.text = hibah.value.toString()
                        binding.tableLayout.addView(tableRow.root)
                    }
                }
                is HibahState.Error -> {
                    Log.e("HibahPengabdianFragment", state.error.localizedMessage.orEmpty(), state.error)
                    Toast.makeText(requireContext(), state.error.localizedMessage.orEmpty(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}