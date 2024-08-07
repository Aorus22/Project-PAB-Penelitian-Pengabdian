package com.l0122030.bagustegar.projectPAB.ui.penelitian_grup_riset.tabel

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.l0122030.bagustegar.projectPAB.R
import com.l0122030.bagustegar.projectPAB.ui.penelitian_grup_riset.IndexAxisValueFormatter

class TabelActivity : AppCompatActivity() {

    private val viewModel: TabelViewModel by viewModels()
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabel_penelitian_grup_riset)

        val facultyName = intent.getStringExtra("EXTRA_FACULTY_NAME") ?: "Nama Fakultas"
        val facultyTextView = findViewById<TextView>(R.id.facultyNameTextView)
        facultyTextView.text = facultyName

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        val barChart = findViewById<BarChart>(R.id.barChart)
        progressIndicator = findViewById(R.id.progress_circular)

        // Tampilkan progress indicator saat data dimuat
        showLoading(true)

        // Memanggil fungsi untuk mengambil data dari ViewModel berdasarkan fakultas
        viewModel.getDataByFaculty(facultyName)

        viewModel.data.observe(this, Observer { data ->
            // Setup tabel dengan data yang diperoleh
            setupTableLayout(tableLayout, data)
            // Setup BarChart dengan data yang diperoleh
            setupBarChart(barChart, data)
            // Sembunyikan progress indicator setelah data selesai dimuat
            showLoading(false)
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressIndicator.visibility = View.VISIBLE
        } else {
            progressIndicator.visibility = View.GONE
        }
    }

    private fun setupTableLayout(tableLayout: TableLayout, data: List<Pair<String, String>>) {

        for ((index, entry) in data.withIndex()) {
            val tableRow = TableRow(this)

            val textViewTahun = TextView(this).apply {
                text = entry.first
                setPadding(8, 24, 8, 24)
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(android.R.color.black))
            }

            val textViewJumlah = TextView(this).apply {
                text = entry.second
                setPadding(8, 24, 8, 24)
                gravity = Gravity.CENTER
                setTextColor(resources.getColor(android.R.color.black))
            }

            tableRow.addView(textViewTahun)
            tableRow.addView(textViewJumlah)

            tableLayout.addView(tableRow)

            // Garis pemisah antar baris tabel
            val separator = View(this).apply {
                layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1)
                setBackgroundColor(resources.getColor(R.color.black))
            }
            tableLayout.addView(separator)
        }
    }

    private fun setupBarChart(barChart: BarChart, data: List<Pair<String, String>>) {
        val entries = data.mapIndexed { index, entry ->
            BarEntry(index.toFloat(), entry.second.toFloat()) // Tidak perlu menambahkan label disini
        }

        val dataSet = BarDataSet(entries, "Jumlah Penelitian Grup Riset")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        val barData = BarData(dataSet)

        barChart.data = barData
        barChart.animateY(1000)
        barChart.setFitBars(true)
        barChart.description.isEnabled = false
        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(data.map { it.first })
        barChart.xAxis.setDrawGridLines(false)
        barChart.invalidate()
    }
}
