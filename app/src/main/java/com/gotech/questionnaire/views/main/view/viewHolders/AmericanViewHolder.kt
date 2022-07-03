package com.gotech.questionnaire.views.main.view.viewHolders

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R
import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.AnswerOption
import com.gotech.questionnaire.views.main.model.Question

class AmericanViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val requiredIndicator: ImageView = view
        .findViewById(R.id.requiredIndicator)
    private val questionTextView: TextView = view
        .findViewById(R.id.textViewQuestion)
    private val radioGroup: RadioGroup = view
        .findViewById(R.id.radioGroup)
    fun bind(question: Question, answer: Answer) {
        questionTextView.text = question.question
        if (question.required)
            requiredIndicator.visibility = View.VISIBLE
        else
            requiredIndicator.visibility = View.GONE
        radioGroup.clearCheck()
        radioGroup.children.forEachIndexed { index, view -> (run {
            if (view is RadioButton) {
                Log.i("TEST",question.options[index].id.toString().plus(" ").plus(question.options[index].text))
                view.text = question.options[index].text
                view.id = question.options[index].id!!
            }
        }) }
        radioGroup.setOnCheckedChangeListener { radioGroup, i -> (run {
            if (radioGroup.getChildAt(i - 1) is RadioButton) {
                val checkedRadioButton = (radioGroup.getChildAt(i - 1) as RadioButton)
                Log.i("TEST", checkedRadioButton.id.toString().plus(" ").plus(checkedRadioButton.text))
                answer.option = AnswerOption(checkedRadioButton.id, checkedRadioButton.text.toString())
            }
        }) }

        if (answer?.option?.id != null) {
            radioGroup.check(answer!!.option!!.id!!)
        }
    }
}