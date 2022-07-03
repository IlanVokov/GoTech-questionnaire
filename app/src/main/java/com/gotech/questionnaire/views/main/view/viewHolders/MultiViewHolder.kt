package com.gotech.questionnaire.views.main.view.viewHolders

import android.view.View
import android.widget.*
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R
import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.AnswerOption
import com.gotech.questionnaire.views.main.model.Question

class MultiViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val requiredIndicator: ImageView = view
        .findViewById(R.id.requiredIndicator)
    private val questionTextView: TextView = view
        .findViewById(R.id.textViewQuestion)
    private val radioGroup: RadioGroup = view
        .findViewById(R.id.radioGroup)
    private val radioOther: RadioButton = view
        .findViewById(R.id.radioOther)
    private val otherEditText: EditText = view
        .findViewById(R.id.otherEditText)
    fun bind(question: Question, answer: Answer) {
        questionTextView.text = question.question
        if (question.required)
            requiredIndicator.visibility = View.VISIBLE
        else
            requiredIndicator.visibility = View.GONE
        radioGroup.clearCheck()
        radioOther.isChecked = false
        otherEditText.setText("")
        radioGroup.children.forEachIndexed { index, view -> (run {
            if (view is RadioButton) {
                view.text = question.options[index].text
                view.id = question.options[index].id!!
            }
        }) }
        radioGroup.setOnCheckedChangeListener { radioGroup, i -> (run {
            radioOther.isChecked = false
            otherEditText.setText("")
            if (radioGroup.getChildAt(i - 1) is RadioButton) {
                val checkedRadioButton = (radioGroup.getChildAt(i - 1) as RadioButton)
                answer.option = AnswerOption(checkedRadioButton.id, checkedRadioButton.text.toString())
            }
        }) }
        otherEditText.addTextChangedListener {
            if (it.toString().isNotEmpty()){
                answer.option!!.text = it.toString()
            }
        }
        radioOther.setOnCheckedChangeListener { compoundButton, b -> (run {
            if (b){
                radioGroup.clearCheck()
                compoundButton.isChecked = true
                answer.option = AnswerOption(null, otherEditText.text.toString())
            }
        }) }
        if (answer?.option?.id != null) {
            radioGroup.check(answer!!.option!!.id!!)
        } else if (answer.option?.text?.isNotEmpty() == true) {
            otherEditText.setText(answer.option!!.text)
            radioOther.isChecked = true
        }
    }
}