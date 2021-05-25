package com.example.remotejobs.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.Animation
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.remotejobs.R
import com.example.remotejobs.common.show
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.view.animation.AnimationUtils
import androidx.navigation.NavController
import androidx.navigation.Navigation

class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        lifecycleScope.launch {
            whenStarted {
                delay(500)
                startBtn.show()
                val animation: Animation =
                        AnimationUtils.loadAnimation(requireContext(), R.anim.topnews_text_view)
                startBtn.animation = animation
                startBtn.setOnClickListener {
                    navController.navigate(R.id.action_startFragment_to_homeFragment)
                }
            }
        }
    }

}