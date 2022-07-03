package com.gotech.questionnaire.views.main.model

data class Question (
    val id: Int,
    val type: String,
    val required: Boolean,
    val question: String,
    val options: List<AnswerOption>,
)