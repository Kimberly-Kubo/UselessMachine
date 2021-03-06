package com.example.uselessmachine

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var time = 0L
        var ticks = 0
        progressBar_main_lookBusy.visibility = View.GONE
        textView_main_lookBusy.visibility = View.GONE

        //lambda below is implementing onClick(v: View!) without mentioning it explicitly
        //lambda --> anonymous function
        //parameter is referenced by "it" --> when there is one parameter in the function
        //$ refers to variable
        //Toast.makeText(this, "Hello this is the text of the button ${(it as Button).text.toString()}", Toast.LENGTH_SHORT).show()

        button_main_lookBusy.setOnClickListener {
            var load = 0
            button_main_selfDestruct.visibility = View.GONE
            button_main_lookBusy.visibility = View.GONE
            switch_main_useless.visibility = View.GONE
            progressBar_main_lookBusy.visibility = View.VISIBLE
            textView_main_lookBusy.visibility = View.VISIBLE

            var loadingTime = object: CountDownTimer(10000, 100)
            {
                override fun onFinish() {
                    button_main_selfDestruct.visibility = View.VISIBLE
                    button_main_lookBusy.visibility = View.VISIBLE
                    switch_main_useless.visibility = View.VISIBLE
                    progressBar_main_lookBusy.visibility = View.GONE
                    textView_main_lookBusy.visibility = View.GONE
                }
                override fun onTick(p0: Long) {
                    progressBar_main_lookBusy.progress = load
                    textView_main_lookBusy.text = "Loading files... $load/100"
                    if (load <= 100)
                    {
                        load++
                    }
                    else
                    {
                        onFinish()
                    }
                }
            }
            loadingTime.start()

        }

        //to listen to a switch, you can use the OnCheckChangedListener
        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            //1. toast status of button
            //2. if button is checked, uncheck it
            if (isChecked){
                Toast.makeText(this, "ON", Toast.LENGTH_SHORT).show()

                val uncheckTimer = object : CountDownTimer((3000..5000).random().toLong(), (500..2000).random().toLong())
                {
                    override fun onFinish() {
                        switch_main_useless.isChecked = false
                    }

                    override fun onTick(p0: Long) {
                        //can have empty function
                    }
                }
                uncheckTimer.start()

                if(!isChecked)
                {
                    Toast.makeText(this, "Canceled!", Toast.LENGTH_SHORT).show()
                    uncheckTimer.cancel()
                }
            }
            else {
                Toast.makeText(this, "OFF", Toast.LENGTH_SHORT).show()
            }
        }
        //3. search CountDownTimer API see what is needed to implement custom timer
        //CountDownTimer creates new thread
        //create subclass of CountDownTimer
        //public abstract class CountDownTimer; extends Object

        button_main_selfDestruct.setOnClickListener {
            button_main_selfDestruct.isEnabled = false
            button_main_lookBusy.isEnabled = false
            switch_main_useless.isEnabled = false
            val timer = object : CountDownTimer(10000, 200)
            {
                override fun onFinish() {
                    finish()
                }
                override fun onTick(p0: Long) {
                    time = p0/1000
                    ticks++
                    button_main_selfDestruct.text = time.toString()
                    if(time < 5)
                    {
                        layout_main.setBackgroundColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
                    }
                    if(ticks%5 == 0)
                    {
                        layout_main.setBackgroundColor(Color.rgb((0..255).random(), (0..255).random(), (0..255).random()))
                    }
                }
            }
            timer.start()
        }

    }
}