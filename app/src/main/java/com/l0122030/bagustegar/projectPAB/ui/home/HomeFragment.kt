package com.l0122030.bagustegar.projectPAB.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.l0122030.bagustegar.projectPAB.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        // Mengatur chart dalam coroutine
        lifecycleScope.launch {
            setChartKegiatanPenelitian()
            setChartSumberDanaPenelitian()

            setChartKegiatanPengabdian()
            setChartSumberDanaPengabdian()

            setChartSebaranRiset()
            setChartSebaranPublikasi()
        }
    }

    // Metode untuk mengatur chart sebaran riset
    private fun setChartSebaranRiset() {
        // Membuat model chart dengan AAChartModel
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie) // Mengatur tipe chart menjadi Pie
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .colorsTheme(arrayOf("#8381E6", "#EE5E81", "#F05E5F", "#81B3E9", "#434348", "#F5AD71")) // Mengatur tema warna untuk data chart
            .dataLabelsEnabled(true) // Mengaktifkan label data pada chart
            .series(arrayOf(
                AASeriesElement()
                    .data(arrayOf(
                        // Menyediakan data untuk chart. Setiap array berisi dua elemen: label dan nilai
                        arrayOf("BUKU", 390),
                        arrayOf("JURNAL", 5060),
                        arrayOf("HAKI", 50),
                        arrayOf("KORAN", 210),
                        arrayOf("PROSIDING",   3630),
                        arrayOf("KEYNOTE", 490),
                        arrayOf("KARYA SENI", 20),
                        arrayOf("REVIEWER", 120),
                    ))))

        // Menggambar chart dengan model yang telah dibuat pada elemen chartSebaranRiset di layout
        binding.chartSebaranRiset.aa_drawChartWithChartModel(aaChartModel)
    }

    /**
     * Metode ini digunakan untuk mengatur chart sebaran publikasi.
     * Chart ini berjenis Pie dan menampilkan data publikasi dalam beberapa kategori.
     */
    private fun setChartSebaranPublikasi() {
        // Membuat model chart dengan AAChartModel
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie) // Mengatur tipe chart menjadi Pie
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .colorsTheme(arrayOf("#8381E6", "#EE5E81", "#F05E5F", "#81B3E9", "#434348", "#F5AD71")) // Mengatur tema warna untuk data chart
            .dataLabelsEnabled(true) // Mengaktifkan label data pada chart
            .series(arrayOf(
                AASeriesElement()
                    .data(arrayOf(
                        // Menyediakan data untuk chart. Setiap array berisi dua elemen: label dan nilai
                        arrayOf("PROSIDING",   3630),
                        arrayOf("KEYNOTE", 490),
                        arrayOf("KARYA SENI", 20),
                        arrayOf("REVIEWER", 120),
                        arrayOf("BUKU", 390),
                        arrayOf("JURNAL", 5060),
                        arrayOf("HAKI", 50),
                        arrayOf("KORAN", 210),
                    ))))

        // Menggambar chart dengan model yang telah dibuat pada elemen chartSebaranPublikasi di layout
        binding.chartSebaranPublikasi.aa_drawChartWithChartModel(aaChartModel)
    }

    /**
     * Metode ini digunakan untuk mengatur chart sumber dana pengabdian.
     * Chart ini berjenis Pie dan menampilkan data sumber dana pengabdian dalam dua kategori: pnbp dan nonpnbp.
     */
    private fun setChartSumberDanaPengabdian() {
        // Membuat model chart dengan AAChartModel
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie) // Mengatur tipe chart menjadi Pie
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .colorsTheme(arrayOf("#92EF83", "#434348")) // Mengatur tema warna untuk data chart
            .dataLabelsEnabled(false) // Menonaktifkan label data pada chart
            .series(arrayOf(
                AASeriesElement()
                    .name("Sumber Dana Pengabdian") // Menyediakan nama untuk data chart
                    .data(arrayOf(
                        // Menyediakan data untuk chart. Setiap array berisi dua elemen: label dan nilai
                        arrayOf("pnbp",   650000),
                        arrayOf("nonpnbp", 300000)
                    ))))

        // Menggambar chart dengan model yang telah dibuat pada elemen chartSumberDanaPengabdian di layout
        binding.chartSumberDanaPengabdian.aa_drawChartWithChartModel(aaChartModel)
    }

    /**
     * Metode ini digunakan untuk mengatur chart kegiatan pengabdian.
     * Chart ini berjenis Column dan menampilkan data kegiatan pengabdian dalam lima tahun terakhir.
     */
    private fun setChartKegiatanPengabdian() {
        // Membuat model chart dengan AAChartModel
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Column) // Mengatur tipe chart menjadi Column
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .subtitle("Pengabdian 5 Tahun Terakhir") // Menyediakan subjudul untuk chart
            .yAxisTitle("Jumlah") // Menyediakan judul untuk sumbu y
            .categories(arrayOf("2014", "2015", "2016", "2017", "2018")) // Menyediakan kategori untuk chart
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Tahun") // Menyediakan nama untuk data chart
                        .color("#81B3E9") // Mengatur warna untuk data chart
                        .data(arrayOf(320, 410, 350, 800, 850)) // Menyediakan data untuk chart
                )
            )

        // Menggambar chart dengan model yang telah dibuat pada elemen chartKegiatanPengabdian di layout
        binding.chartKegiatanPengabdian.aa_drawChartWithChartModel(aaChartModel)
    }

    /**
     * Metode ini digunakan untuk mengatur chart sumber dana penelitian.
     * Chart ini berjenis Pie dan menampilkan data sumber dana penelitian dalam dua kategori: pnbp dan nonpnbp.
     */
    private fun setChartSumberDanaPenelitian() {
        // Membuat model chart dengan AAChartModel
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie) // Mengatur tipe chart menjadi Pie
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .colorsTheme(arrayOf("#92EF83", "#434348")) // Mengatur tema warna untuk data chart
            .dataLabelsEnabled(false) // Menonaktifkan label data pada chart
            .series(arrayOf(
                AASeriesElement()
                    .name("Sumber Dana Penelitian") // Menyediakan nama untuk data chart
                    .data(arrayOf(
                        // Menyediakan data untuk chart. Setiap array berisi dua elemen: label dan nilai
                        arrayOf("pnbp",   900000),
                        arrayOf("nonpnbp", 100000)
                    ))))

        // Menggambar chart dengan model yang telah dibuat pada elemen chartSumberDanaPenelitian di layout
        binding.chartSumberDanaPenelitian.aa_drawChartWithChartModel(aaChartModel)
    }

    /**
     * Metode ini digunakan untuk mengatur chart kegiatan penelitian.
     * Chart ini berjenis Column dan menampilkan data kegiatan penelitian dalam lima tahun terakhir.
     */
    private fun setChartKegiatanPenelitian() {
        // Membuat model chart dengan AAChartModel
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Column) // Mengatur tipe chart menjadi Column
            .backgroundColor("#F9F9FB") // Mengatur warna latar belakang chart
            .subtitle("Penelitian 5 Tahun Terakhir") // Menyediakan subjudul untuk chart
            .yAxisTitle("Jumlah") // Menyediakan judul untuk sumbu y
            .categories(arrayOf("2014", "2015", "2016", "2017", "2018")) // Menyediakan kategori untuk chart
            .series(
                arrayOf(
                    AASeriesElement()
                        .name("Tahun") // Menyediakan nama untuk data chart
                        .color("#81B3E9") // Mengatur warna untuk data chart
                        .data(arrayOf(450, 610, 625, 580, 180)) // Menyediakan data untuk chart
                )
            )

        // Menggambar chart dengan model yang telah dibuat pada elemen chartKegiatanPenelitian di layout
        binding.chartKegiatanPenelitian.aa_drawChartWithChartModel(aaChartModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}