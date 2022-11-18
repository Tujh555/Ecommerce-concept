package ru.effectivemobile.ecommerceconcept.mylibrary

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.effectivemobile.ecommerceconcept.mylibrary.databinding.FragmentSplashBinding
import ru.effectivemobile.ecommerceconcept.navigation.navigate

internal class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val transitionToNextFragment = Runnable {
        navigate(R.id.action_splashFragment_to_homePageFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(transitionToNextFragment , DELAY_MILLISECONDS)
    }

    private companion object {
        const val DELAY_MILLISECONDS = 4000L
    }
}