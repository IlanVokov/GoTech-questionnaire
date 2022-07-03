package com.gotech.questionnaire.networking

import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.Question
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface RetrofitClient {

    @GET("/questions")
    suspend fun getQuestions() : Response<List<Question>>

    @POST("/answers")
    suspend fun postAnswers(@Body answers: List<Answer>) : Response<List<Answer>>

    companion object {
        var retrofitClient: RetrofitClient? = null
        fun getInstance(baseUrl: String) : RetrofitClient {
            if (retrofitClient == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitClient = retrofit.create(RetrofitClient::class.java)
            }
            return retrofitClient!!
        }

    }
}