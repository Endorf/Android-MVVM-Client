package com.mvvm.sharednotes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mvvm.sharednotes.databinding.ActivityRoutingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutingBinding
    private lateinit var splashScreen: SplashScreen

    private val viewModel: RoutingActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        splashScreen = initSplashScreen()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_routing)

        viewModel.retrieveUser()

        viewModel.isUserSignedIn.observe(this) { isUserSignedIn ->
            if (isUserSignedIn) TODO("implement notes module")
            else findNavController(R.id.nav_host_fragment_content_main)

            splashScreen.setKeepOnScreenCondition { false }
        }
    }

    private fun initSplashScreen() = installSplashScreen().apply {
        setKeepOnScreenCondition { true }
    }
}