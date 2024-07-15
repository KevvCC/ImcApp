package com.example.nada.imcapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nada.R


class ImcResultActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvImcResult: TextView
    private lateinit var tvImcDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)
        val result: Double = intent.extras?.getDouble("imc_result") ?: -1.0
        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initComponent() {
        tvResult = findViewById(R.id.tvResult)
        tvImcResult = findViewById(R.id.tvImcResult)
        tvImcDescription = findViewById(R.id.tvImcDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }


    private fun initUI(result: Double) {
        tvImcResult.text = result.toString()
        when(result){
            in 0.0..18.5 -> {
                tvResult.text = getString(R.string.underweight)
                tvResult.setTextColor(getColor(R.color.underweight))
                tvImcDescription.text = getString(R.string.imc_description_underweight)
            }
            in 18.6..24.9 -> {
                tvResult.text = getString(R.string.normal_weight)
                tvResult.setTextColor(getColor(R.color.normalweight))
                tvImcDescription.text = getString(R.string.imc_description_normal_weight)
            }
            in 25.0..29.9 -> {
                tvResult.text = getString(R.string.overweight)
                tvResult.setTextColor(getColor(R.color.overweight))
                tvImcDescription.text = getString(R.string.imc_description_overweight)
            }
            in 30.0..99.9 -> {
                tvResult.text = getString(R.string.obesity)
                tvResult.setTextColor(getColor(R.color.obesity))
                tvImcDescription.text = getString(R.string.imc_description_obesity)
            }
            else -> {
                tvResult.text = getString(R.string.errorMessage)
                tvImcResult.text = getString(R.string.errorMessage)
                tvImcDescription.text = getString(R.string.errorMessage)
            }
        }
    }
}