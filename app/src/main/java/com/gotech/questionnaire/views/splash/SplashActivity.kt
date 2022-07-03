package com.gotech.questionnaire.views.splash

import android.content.Intent
import android.os.Bundle
import com.gotech.questionnaire.views.base.BaseActivity
import com.gotech.questionnaire.views.main.view.MainActivity

class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}