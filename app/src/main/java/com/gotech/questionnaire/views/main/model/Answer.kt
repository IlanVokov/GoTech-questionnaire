package com.gotech.questionnaire.views.main.model

data class Answer(
    val questionId: Int,
    var text: String?,
    var option: AnswerOption?,
)