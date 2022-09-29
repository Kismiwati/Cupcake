package com.kismiwati.cupcake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.kismiwati.cupcake.R

/*
 * bagian ini adalah aktivitas untuk alur pesanan cupcake
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bagian ini untuk mengambil NavController dari NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // bagian ini digunakan untuk menyiapkan bilah tindakan untuk digunakan dengan NavContriller
        setupActionBarWithNavController(navController)
    }
}