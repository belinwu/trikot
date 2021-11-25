package com.mirego.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import com.mirego.sample.ui.HomeView
import com.mirego.sample.viewmodels.home.HomeNavigationDelegate
import com.mirego.sample.viewmodels.home.HomeViewModel
import com.mirego.sample.viewmodels.home.HomeViewModelController
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelActivity

class HomeActivity : ViewModelActivity<HomeViewModelController, HomeViewModel, HomeNavigationDelegate>(), HomeNavigationDelegate {

    override val viewModelController: HomeViewModelController by lazy {
        getViewModelController(HomeViewModelController::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeView(viewModel)
        }
    }
}
