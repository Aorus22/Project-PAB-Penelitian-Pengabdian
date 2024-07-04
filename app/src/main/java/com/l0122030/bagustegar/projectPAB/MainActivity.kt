package com.l0122030.bagustegar.projectPAB

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.l0122030.bagustegar.projectPAB.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /**
     * Method dipanggil ketika aktivitas dimulai.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur toolbar untuk bertindak sebagai ActionBar untuk activity ini
        setSupportActionBar(binding.toolbar)

        // Mendapatkan DrawerLayout dan NavigationView dari binding
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // Mendapatkan NavController untuk fragmen konten utama
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Membuat AppBarConfiguration dengan destination dan DrawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_hibah_pengabdian,
                R.id.nav_activity,
                R.id.nav_penelitian,
                R.id.nav_penelitian_grup_riset,
                R.id.nav_riwayat,
                R.id.nav_rekap,
                R.id.nav_sumber_biaya,
                R.id.nav_prosiding,
                R.id.nav_haki,
                R.id.nav_program_kerja,
                R.id.nav_koran,
                R.id.nav_keynote_speaker,
                R.id.nav_reviewer
            ), drawerLayout
        )
        // Mengatur ActionBar yang dikembalikan oleh setSupportActionBar() untuk digunakan dengan NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Mengatur NavigationView untuk digunakan dengan NavController
        navView.setupWithNavController(navController)
    }

    /**
     * Metode ini dipanggil untuk mengisi menu jika ada.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * Metode ini dipanggil ketika item di menu diklik.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_facebook -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
                startActivity(intent)
                true
            }
            R.id.action_x -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://x.com/"))
                startActivity(intent)
                true
            }
            R.id.action_instagram -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/"))
                startActivity(intent)
                true
            }
            R.id.action_linkedin -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/"))
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Metode ini dipanggil ketika tombol navigasi atas dipilih.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}