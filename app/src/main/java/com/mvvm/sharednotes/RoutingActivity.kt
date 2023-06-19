package com.mvvm.sharednotes

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mvvm.sharednotes.databinding.ActivityRoutingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutingBinding

    private val viewModel: RoutingActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val splashScreen = initSplashScreen()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_routing)
        viewModel.keepScreenOnCondition.observe(this) {
            splashScreen.setKeepOnScreenCondition { it }
        }
        viewModel.isUserSignedIn.observe(this) { isUserSignedIn ->
            if (isUserSignedIn) TODO("implement notes module")
            else findNavController(R.id.nav_host_fragment_content_main)
        }

        viewModel.retrieveUser()
    }

    private fun initSplashScreen() = installSplashScreen().apply {
        setKeepOnScreenCondition { true }
        setOnExitAnimationListener {
            val fadeAnim = ObjectAnimator.ofFloat(
                it.view,
                View.ALPHA,
                1f,
                0f
            )
            fadeAnim.duration = ANIMATION_DURATION
            fadeAnim.interpolator = AccelerateInterpolator()
            fadeAnim.start()
        }
    }
    companion object {
        private const val ANIMATION_DURATION = 300L
    }
}