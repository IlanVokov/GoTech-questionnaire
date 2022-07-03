package com.gotech.questionnaire.views.main.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R
import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.Question
import com.gotech.questionnaire.views.main.view.viewHolders.AmericanViewHolder
import com.gotech.questionnaire.views.main.view.viewHolders.MultiViewHolder
import com.gotech.questionnaire.views.main.view.viewHolders.TextViewHolder

class QuestionsAdapter( var questions: List<Question>, var answers: List<Answer>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> ( return AmericanViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_question_american, parent, false)))
            2 -> ( return TextViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_question_text, parent, false)))
            else -> { return MultiViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_question_multi, parent, false)) }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(questions[position].type){
            "americano" -> ( return 1 )
            "texticano" -> ( return 2 )
            else -> { return 3 }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(questions[position].type){
            "americano" -> (holder as AmericanViewHolder).bind(questions[position], answers[position])
            "texticano" -> (holder as TextViewHolder).bind(questions[position], answers[position])
            "multicano" -> (holder as MultiViewHolder).bind(questions[position], answers[position])
        }
    }
    override fun getItemCount(): Int {
        return if (questions != null) questions.size else 0
    }
}