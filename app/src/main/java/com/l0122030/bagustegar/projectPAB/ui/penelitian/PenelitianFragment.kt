package com.l0122030.bagustegar.projectPAB.ui.penelitian

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.l0122030.bagustegar.projectPAB.R
import com.l0122030.bagustegar.projectPAB.data.model.penelitian.Penelitian
import com.l0122030.bagustegar.projectPAB.databinding.FragmentPenelitianBinding
import com.l0122030.bagustegar.projectPAB.ui.penelitian.tabel.TabelActivity

class PenelitianFragment : Fragment() {

    private var _binding: FragmentPenelitianBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PenelitianViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPenelitianBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.faculties.observe(viewLifecycleOwner, Observer { faculties ->
            showLoading(true) // Tampilkan CircularProgress saat memuat data
            setupGridLayout(faculties)
            setupBarChart(faculties)
            showLoading(false) // Sembunyikan CircularProgress setelah data selesai dimuat
        })

        return view
    }

    private fun setupGridLayout(faculties: List<Penelitian>) {
        binding.buttonsContainer.removeAllViews()
        for ((index, faculty) in faculties.withIndex()) {
            val departmentName = faculty.namaFakultas
            val researchCount = faculty.totalPenelitian
            val urlFaculty = faculty.urlPenelitianFakultas

            val buttonLayout = createButtonLayout(departmentName, researchCount, urlFaculty)

            val rowSpec = GridLayout.spec(index / 2, 1f)
            val colSpec = GridLayout.spec(index % 2, 1f)

            val layoutParams = GridLayout.LayoutParams(rowSpec, colSpec)
            layoutParams.width = 0
            layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT
            layoutParams.setMargins(8, 8, 8, 8)
            buttonLayout.layoutParams = layoutParams

            binding.buttonsContainer.addView(buttonLayout)
        }
    }

    private fun createButtonLayout(departmentName: String, researchCount: Int, departmentURL:String): View {
        val inflater = LayoutInflater.from(context)
        val cardView = inflater.inflate(R.layout.button_layout, binding.buttonsContainer, false) as CardView

        val icon = cardView.findViewById<ImageView>(R.id.icon)
        val department = cardView.findViewById<TextView>(R.id.departmentName)
        val detailButton = cardView.findViewById<Button>(R.id.detailButton)

        // Temporary placeholder icon, replace with actual drawableResId
        icon.setImageResource(R.drawable.ic_placeholder)
        department.text = departmentName

        detailButton.setOnClickListener {
            goToTabelActivity(departmentURL)
        }

        return cardView
    }

    private fun goToTabelActivity(departmentName: String) {
        val intent = Intent(context, TabelActivity::class.java).apply {
            putExtra("EXTRA_FACULTY_NAME", departmentName)
        }
        startActivity(intent)
    }

    private fun setupBarChart(faculties: List<Penelitian>) {
        val entries = faculties.mapIndexed { index, faculty ->
            BarEntry(index.toFloat(), faculty.totalPenelitian.toFloat())
        }

        val dataSet = BarDataSet(entries, "Jumlah Penelitian")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val barData = BarData(dataSet)
        binding.barChart.data = barData
        binding.barChart.animateY(1000)
        binding.barChart.setFitBars(true)
        binding.barChart.description.isEnabled = false
        binding.barChart.xAxis.setDrawGridLines(false)
        binding.barChart.xAxis.valueFormatter = IndexAxisValueFormatter(faculties.map { it.namaFakultas.substringAfterLast(" ") })
        binding.barChart.invalidate()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressCircular.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
