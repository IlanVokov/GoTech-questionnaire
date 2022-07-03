package com.gotech.questionnaire.views.main.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gotech.questionnaire.BuildConfig
import com.gotech.questionnaire.networking.RetrofitClient
import com.gotech.questionnaire.views.main.repository.MainRepo
import com.gotech.questionnaire.views.main.viewModel.MainViewModel

class MainViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(MainRepo(RetrofitClient.getInstance(BuildConfig.BASE_URL))) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
