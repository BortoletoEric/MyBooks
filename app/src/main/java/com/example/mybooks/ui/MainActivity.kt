package com.example.mybooks.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.findNavController
import com.example.mybooks.R
import com.example.mybooks.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupNavigation()
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.setOnItemSelectedListener { item ->
            // 2. Verificamos se o destino atual é o Detalhes
            if (navController.currentDestination?.id == R.id.navigation_details) {
                // Use o navController para voltar, NUNCA o supportFragmentManager manual aqui
                navController.popBackStack()
            }

            // 3. Só navegamos se o clique for em uma aba DIFERENTE da que já estamos
            if (item.itemId != navController.currentDestination?.id) {
                // O onNavDestinationSelected cuida de limpar o histórico para não empilhar
                // as telas infinitamente ao clicar nos ícones.
                androidx.navigation.ui.NavigationUI.onNavDestinationSelected(item, navController)
            }

            true // Indica que o item foi selecionado visualmente
        }
    }
}