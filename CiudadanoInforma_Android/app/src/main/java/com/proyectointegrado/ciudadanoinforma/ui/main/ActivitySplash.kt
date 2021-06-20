package com.proyectointegrado.ciudadanoinforma.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.proyectointegrado.ciudadanoinforma.R
import com.proyectointegrado.ciudadanoinforma.ui.login.LoginActivity


//Clase con la que se inicia la aplicación que muestra el logo de la misma.
class ActivitySplash : AppCompatActivity() {


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.drawable.gradient)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startTimer()
    }

    fun startTimer() {
        val countDownTimer: CountDownTimer = object : CountDownTimer(1 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                startActivity(Intent(this@ActivitySplash, LoginActivity::class.java))
                this@ActivitySplash.finish()
            }
        }
        countDownTimer.start()
    }

}
