package com.gotech.questionnaire.views.base

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.gotech.questionnaire.R

open class BaseActivity : AppCompatActivity() {

    var containerId: Int? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showError(errorMessage: String?) {
        if (errorMessage != null){
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    fun showProgressBar(loading: Boolean):Unit{
        if (progressBar == null && containerId != null){
            progressBar = ProgressBar(this)
            progressBar!!.indeterminateTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.purple_500))
            progressBar!!.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            val params = progressBar!!.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            params.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            val container = findViewById<ConstraintLayout>(containerId!!);
            container.addView(progressBar)
        }
        when (loading) {
            true -> progressBar!!.visibility = View.VISIBLE
            false -> progressBar!!.visibility = View.GONE
        }
    }
}