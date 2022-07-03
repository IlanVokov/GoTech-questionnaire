package com.gotech.questionnaire.views.main.view.viewHolders

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R
import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.Question

class TextViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val requiredIndicator: ImageView = view
        .findViewById(R.id.requiredIndicator)
    private val questionTextView: TextView = view
        .findViewById(R.id.textViewQuestion)
    private val answerEditText: EditText = view
        .findViewById(R.id.answerEditText)
    fun bind(question: Question, answer: Answer) {
        questionTextView.text = question.question
        if (question.required)
            requiredIndicator.visibility = View.VISIBLE
        else
            requiredIndicator.visibility = View.GONE
        answerEditText.setText("")
        answerEditText.addTextChangedListener {
            if (it.toString().isNotEmpty()){
                answer.text = it.toString()
            }
        }
        if (answer?.text != null) {
            answerEditText.setText(answer.text)
        }
    }
}