package com.gotech.questionnaire.views.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gotech.questionnaire.views.main.model.Answer
import com.gotech.questionnaire.views.main.model.Question
import com.gotech.questionnaire.views.main.repository.MainRepo
import com.gotech.questionnaire.views.main.viewModel.dataStates.MainDataState
import com.gotech.questionnaire.views.main.viewModel.dataStates.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val mainRepo: MainRepo) : ViewModel() {

    private val _mainViewState = MutableLiveData<MainViewState>()
    val mainViewState : LiveData<MainViewState> = _mainViewState

    private val _mainDataState = MutableLiveData<MainDataState>()
    val mainDataState : LiveData<MainDataState> = _mainDataState

    var isFirstTime: Boolean? = null

    init{
        if (isFirstTime == null){
            _mainViewState.postValue(MainViewState(true, null))
            viewModelScope.launch(Dispatchers.IO){
                fetchData()
                isFirstTime = false
            }
        }

    }

    private suspend fun fetchData(){
        val response = mainRepo.getQuestions()
        withContext(Dispatchers.Main){
            if (!response.isSuccessful)
                _mainViewState.postValue(MainViewState(false, response.message()))
            else if (response.body() != null) {
                _mainViewState.postValue(MainViewState(false, null))
                val answers = ArrayList<Answer>();
                response.body()!!.forEach {
                    var ans = Answer(it.id, null, null)
                    answers.add(ans)
                }
                _mainDataState.postValue(MainDataState(response.body(), answers))
            } else
                _mainViewState.postValue(MainViewState(false, "No questions found!"))
        }
    }

    fun postData(answers: List<Answer>){
        if (!isValidAnswers(answers, _mainDataState.value!!.questionList)){
            _mainViewState.postValue(MainViewState(false, "Please answer all the required questions!"))
        } else {
            _mainViewState.postValue(MainViewState(true, null))
            viewModelScope.launch(Dispatchers.IO){
                val response = mainRepo.postAnswers(answers)
                withContext(Dispatchers.Main){
                    if (!response.isSuccessful)
                        _mainViewState.postValue(MainViewState(false, response.message()))
                    else if (response.body() != null) {
                        _mainViewState.postValue(MainViewState(false, "Success"))
                    } else
                        _mainViewState.postValue(MainViewState(false, "Success"))
                }
            }
        }
    }

    private fun isValidAnswers(answers: List<Answer>, questionList: List<Question>?): Boolean {
        var isValid = true
        questionList?.forEachIndexed { index, question -> (run {
            if (question.required){
                when(question.type){
                    "texticano" -> {
                        if (answers[index].text.isNullOrEmpty()){
                            isValid = false
                        }
                    }
                    else -> {
                        if (answers[index].option?.text.isNullOrEmpty()){
                            isValid = false
                        }
                    }
                }
            }
        }) }
        return isValid
    }
}