package com.gotech.questionnaire.views.main.repository

import com.gotech.questionnaire.networking.RetrofitClient
import com.gotech.questionnaire.views.main.model.Answer

class MainRepo(private val retrofitClient: RetrofitClient) {
    suspend fun getQuestions() = retrofitClient.getQuestions()
    suspend fun postAnswers(answers:List<Answer>) = retrofitClient.postAnswers(answers)
}
