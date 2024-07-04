package com.l0122030.bagustegar.projectPAB.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.l0122030.bagustegar.projectPAB.data.model.AktivitasGrupRiset
import com.l0122030.bagustegar.projectPAB.data.model.AktivitasPenelitian
import com.l0122030.bagustegar.projectPAB.data.model.HibahPengabdian
import com.l0122030.bagustegar.projectPAB.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    // Metode ini dipanggil untuk membuat tampilan fragmen
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    // Metode ini dipanggil setelah tampilan fragmen dibuat
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = ""

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        observeData()
    }

    private fun observeData() {
        viewModel.aktivitasPenelitian.observe(viewLifecycleOwner) { uiState ->
            binding.progressPenelitian.isVisible = false
            binding.chartKegiatanPenelitian.isVisible = false

            when (uiState) {
                is Resource.Loading -> {
                    binding.progressPenelitian.isVisible = true
                }
                is Resource.Success -> {
                    setChartKegiatanPenelitian(uiState.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.aktivitasGrupRiset.observe(viewLifecycleOwner) { uiState ->
            binding.progressGrupRiset.isVisible = false
            binding.chartSebaranRiset.isVisible = false

            when (uiState) {
                is Resource.Loading -> {
                    binding.progressGrupRiset.isVisible = true
                }
                is Resource.Success -> {
                    setChartSebaranRiset(uiState.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.aktivitasHibah.observe(viewLifecycleOwner) { uiState ->
            binding.progressHibahPengabdian.isVisible = false
            binding.chartHibahPengabdian.isVisible = false

            when (uiState) {
                is Resource.Loading -> {
                    binding.progressHibahPengabdian.isVisible = true
                }
                is Resource.Success -> {
                    setChartHibah(uiState.data)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setChartHibah(data: List<HibahPengabdian>) {
        binding.chartHibahPengabdian.isVisible = true
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Column)
            .backgroundColor("#F9F9FB")
            .yAxisTitle("Jumlah")
            .categories(data.map { it.faculty }.toTypedArray())
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Fakultas")
                        .color("#F05E5F")
                        .data(data.map { it.value }.toTypedArray())
                )
            )

        binding.chartHibahPengabdian.aa_drawChartWithChartModel(aaChartModel)
    }

    /**
     * Metode ini digunakan untuk mengatur chart kegiatan penelitian.
     * Chart ini berjenis Column dan menampilkan data kegiatan penelitian dalam lima tahun terakhir.
     */
    private fun setChartKegiatanPenelitian(data: List<AktivitasPenelitian>) {
        // Membuat model chart dengan AAChartModel
        binding.chartKegiatanPenelitian.isVisible = true
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Column) // Mengatur tipe chart menjadi Column
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .subtitle("Penelitian 5 Tahun Terakhir") // Menyediakan subjudul untuk chart
            .yAxisTitle("Jumlah") // Menyediakan judul untuk sumbu y
            .categories(data.map { it.year.toString() }.toTypedArray()) // Menyediakan kategori untuk sumbu x
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Tahun") // Menyediakan nama untuk data chart
                        .color("#81B3E9") // Mengatur warna untuk data chart
                        .data(data.map { it.value }.toTypedArray()) // Menyediakan data untuk chart
                )
            )

        // Menggambar chart dengan model yang telah dibuat pada elemen chartKegiatanPenelitian di layout
        binding.chartKegiatanPenelitian.aa_drawChartWithChartModel(aaChartModel)
    }

    // Metode untuk mengatur chart sebaran riset
    private fun setChartSebaranRiset(data: List<AktivitasGrupRiset>) {
        // Membuat model chart dengan AAChartModel
        binding.chartSebaranRiset.isVisible = true
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie) // Mengatur tipe chart menjadi Pie
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .colorsTheme(arrayOf("#8381E6", "#EE5E81", "#F05E5F", "#81B3E9", "#434348", "#F5AD71")) // Mengatur tema warna untuk data chart
            .dataLabelsEnabled(true) // Mengaktifkan label data pada chart
            .series(
                arrayOf(
                    AASeriesElement().data(
                        data.map {
                            arrayOf(it.name, it.value)
                        }.toTypedArray()
                    )
                )
            ) // Menyediakan data untuk chart

        // Menggambar chart dengan model yang telah dibuat pada elemen chartSebaranRiset di layout
        binding.chartSebaranRiset.aa_drawChartWithChartModel(aaChartModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}