package dev.thenoveltyseeker.articlestime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import dev.thenoveltyseeker.articlestime.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val navHostFragment by lazy(LazyThreadSafetyMode.NONE) {
        binding.navHostFragment.getFragment() as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}