package com.jaguar.toolkit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.jaguar.toolkit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_all_tools, R.id.navigation_fav_tools, R.id.navigation_settings
        ).build()
        val navController = findNavController(R.id.navhost)
        setupWithNavController(binding.incNavbar.navView, navController)

        val toolbar: Toolbar = binding.incAppbar.materialToolbar
        toolbar.setTitle(R.string.app_name)
        toolbar.setLogo(R.mipmap.ic_logo)
        setSupportActionBar(toolbar)
    }
}