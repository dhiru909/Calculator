package com.justdevelopers.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException
import kotlin.math.roundToLong

class MainActivity : AppCompatActivity() {
    private var tvInput :TextView?=null
    var lastNumeric:Boolean =true
    var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
//        Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)
        lastNumeric=true
        lastDot=false
    }

    fun onClear(view: android.view.View) {
        tvInput?.text=""
    }
    fun onDot(view: View){
        if(lastNumeric&& !lastDot){
            tvInput?.append(".")
            lastDot=true
            lastNumeric=false
        }
    }
    fun onDelete(view:View){

    }

    fun onResult(view: View){

        if (lastNumeric){

            var tvValue: String = tvInput?.text.toString()
            var prefix=""
            if(tvValue.startsWith("-")){
                prefix="-"
                tvValue=tvValue.substring(1)
            }
            try {
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    if (splitValue.size > 1) {
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                    }
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    if (splitValue.size > 1) {
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                    }
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    if (splitValue.size > 1) {
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        val num=(one.toDouble() / two.toDouble())
                        tvInput?.text = String.format("%.3f", num)
                    }
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    if (splitValue.size > 1) {
                        var one = splitValue[0]
                        var two = splitValue[1]
                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }
                        tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }


    }

    fun onOperator(view: android.view.View) {
        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastDot=false
                lastNumeric = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/")||value.contains("*")||value.contains("-")||value.contains("+")
        }
    }

}