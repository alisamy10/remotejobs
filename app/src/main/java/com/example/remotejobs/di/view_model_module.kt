package com.example.remotejobs.di


import com.example.remotejobs.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
   viewModel { HomeViewModel(get()) }

}