package com.l0122030.bagustegar.projectPAB.ui.penelitian.tabel

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
import com.l0122030.bagustegar.projectPAB.R
import com.l0122030.bagustegar.projectPAB.ui.penelitian.IndexAxisValueFormatter

class TabelActivity : AppCompatActivity() {

    private val viewModel: TabelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabel_penelitian)

        val facultyName = intent.getStringExtra("EXTRA_FACULTY_NAME") ?: "Nama Fakultas"
        val facultyTextView = findViewById<TextView>(R.id.facultyNameTextView)
        facultyTextView.text = facultyName

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        val barChart = findViewById<BarChart>(R.id.barChart)

        // Memanggil fungsi untuk mengambil data dari ViewModel berdasarkan fakultas
        viewModel.getDataByFaculty(facultyName)

        viewModel.data.observe(this, Observer { data ->
            // Setup tabel dengan data yang diperoleh
            setupTableLayout(tableLayout, data)
            // Setup BarChart dengan data yang diperoleh
            setupBarChart(barChart, data)
        })
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
            BarEntry(index.toFloat(), entry.second.toFloat(), entry.first) // Menambahkan label pada setiap bar
        }

        val dataSet = BarDataSet(entries, "Jumlah Penelitian")
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
