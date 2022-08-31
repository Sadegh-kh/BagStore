package com.example.bagstore.di

import com.example.bagstore.ui.features.singUpScreen.SingUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules= module {
viewModel { SingUpViewModel() }
}