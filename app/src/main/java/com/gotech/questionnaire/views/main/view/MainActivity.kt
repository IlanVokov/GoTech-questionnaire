package com.gotech.questionnaire.views.main.view


import android.os.Bundle
import android.view.View

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R
import com.gotech.questionnaire.views.base.BaseActivity
import com.gotech.questionnaire.views.main.factory.MainViewModelFactory
import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.Question
import com.gotech.questionnaire.views.main.view.adapters.FooterAdapter
import com.gotech.questionnaire.views.main.view.adapters.HeaderAdapter
import com.gotech.questionnaire.views.main.view.adapters.QuestionsAdapter
import com.gotech.questionnaire.views.main.viewModel.MainViewModel

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var footerAdapter: FooterAdapter
    private lateinit var questionsAdapter: QuestionsAdapter

    private lateinit var concatAdapter: ConcatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this@MainActivity, MainViewModelFactory())
            .get(MainViewModel::class.java)
        viewModel.mainViewState.observe(this@MainActivity, Observer {
            val viewState = it ?: return@Observer
            showProgressBar(viewState.isLoading)
            showError(viewState.errorMessage)
        })
        viewModel.mainDataState.observe(this@MainActivity, Observer {
            val dataState = it ?: return@Observer
            if (dataState.questionList != null)
                setData(dataState.questionList!!, dataState.answerList!!)
        })
    }

    private fun initView() {
        containerId = R.id.container
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        headerAdapter = HeaderAdapter(false)
        footerAdapter = FooterAdapter(false, onSubmitClickListener)
        questionsAdapter = QuestionsAdapter(ArrayList<Question>(), ArrayList<Answer>())
        concatAdapter = ConcatAdapter(
            headerAdapter,
            questionsAdapter,
            footerAdapter
        )
        recyclerView.adapter = concatAdapter
    }

    private val onSubmitClickListener = View.OnClickListener{
        viewModel.postData(questionsAdapter.answers)
    }

    private fun setData(questions: List<Question>, answers: List<Answer>){
        if (questionsAdapter != null){
            questionsAdapter.questions = questions
            questionsAdapter.answers = answers
            questionsAdapter.notifyDataSetChanged()
            headerAdapter.show = true
            headerAdapter.notifyDataSetChanged()
            footerAdapter.show = true
            footerAdapter.notifyDataSetChanged()
        }
    }

}