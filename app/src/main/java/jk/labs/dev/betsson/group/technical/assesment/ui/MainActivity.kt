package jk.labs.dev.betsson.group.technical.assesment.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import jk.labs.dev.betsson.group.technical.assesment.R
import jk.labs.dev.betsson.group.technical.assesment.databinding.ActivityMainBinding
import jk.labs.dev.betsson.group.technical.assesment.ui.viewmodels.LoadingViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private val loadingViewModel by viewModels<LoadingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        setupObservers()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug", "Permission granted!")
            } else {
                Toast.makeText(
                    this,
                    "To improve your experience, please, allow Location permission",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupObservers() {
        loadingViewModel.loadingUiState.observe(this){
            updateLoading(it)
        }
    }

    private fun setupActionBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.findNavController()
        setSupportActionBar(binding.toolbar)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.placesFragment -> supportActionBar?.hide()
                R.id.placeDetailFragment -> supportActionBar?.show()
            }
        }

        setupActionBarWithNavController(navController)
        binding.toolbar.setupWithNavController(navController)
    }

    private fun updateLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbLoading.show()
            binding.navHostFragment.visibility = View.GONE
            binding.appBar.visibility = View.GONE
        }
        else {
            binding.pbLoading.hide()
            binding.navHostFragment.visibility = View.VISIBLE
            binding.appBar.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val PERMISSION_ID = 10001
    }
}
