package com.kocerlabs.paparauiclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kocerlabs.paparauiclone.data.database.UserPreferences
import com.kocerlabs.paparauiclone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences
    // Activity'den userPreferences'ı alıyorum ki, tüm activity boyunca gereken fragment'lardan kullanabileyim.
    // gerekli yerde aşağıdaki çağrıyı yapacağım. Main activity başlatılırken okuyacağı için tekrar okumadan alabilecek.
    // bunu first() fonksiyonu sayesinde yapabiliyorum.

    //    val token = runBlocking { userPreferences.authToken.first() }

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runBlocking {
            userPreferences.authToken.first()
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)


//        setupActionBarWithNavController(navController, appBarConfiguration)

        /**
         * Burada toolbar yardımıyla fragment'ların isimlerinin yukarıda görünmesini sağladık.
         * Bu isimleri değiştirmek istersen, navGraph'a gidip oradan label'ları değiştirerek bunu yapabilirsin.
         */
    }


}