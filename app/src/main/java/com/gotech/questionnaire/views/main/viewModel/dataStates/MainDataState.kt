package com.gotech.questionnaire.views.main.viewModel.dataStates

import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.Question

data class MainDataState (val questionList: List<Question>? = null,
                          val answerList: List<Answer>? = null)