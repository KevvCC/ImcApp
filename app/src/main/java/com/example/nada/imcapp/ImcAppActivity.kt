package com.example.nada.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.nada.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcAppActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var height = 120
    private var age: Int = 25
    private var weight: Int = 30

    private lateinit var cvMale: CardView
    private lateinit var cvFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnCalculate: AppCompatButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAdditionAge: FloatingActionButton
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAdditionWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var tvWeight: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_app)
        initComponent()
        initListener()
        initUI()
    }

    private fun initComponent() {
        cvMale = findViewById(R.id.cvMale)
        cvFemale = findViewById(R.id.cvFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubtractAge = findViewById(R.id.btnSubstractAge)
        btnAdditionAge = findViewById(R.id.btnAdditionAge)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractWeight = findViewById(R.id.btnSubstractWeight)
        btnAdditionWeight = findViewById(R.id.btnaditionWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListener() {
        cvMale.setOnClickListener {
            changeComponent()
            setColor()
        }
        cvFemale.setOnClickListener {
            changeComponent()
            setColor()
        }
        rsHeight.addOnChangeListener {_, value,_->
            val dFormat = DecimalFormat("#.##")
            height = dFormat.format(value).toInt()
            tvHeight.text = getString(R.string.height_text, height)
        }
        btnSubtractAge.setOnClickListener {
            age--
            setAge()
        }
        btnAdditionAge.setOnClickListener{
            age++
            setAge()
        }
        btnSubtractWeight.setOnClickListener {
            weight--
            setWeight()
        }
        btnAdditionWeight.setOnClickListener {
            weight++
            setWeight()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            goToResult(result)
        }
    }

    private fun initUI() {
        setColor()
        setAge()
        setWeight()
    }

    private fun calculateIMC(): Double{
        val dFormat = DecimalFormat("#.##")
        val imc = weight / (height.toDouble() / 100 * height.toDouble()/100)
        return dFormat.format(imc).toDouble()
    }

    private fun goToResult(result: Double) {
        val intent = Intent(this, ImcResultActivity::class.java)
        intent.putExtra("imc_result", result)
        startActivity(intent)
    }

    private fun setWeight(){
        tvWeight.text = getString(R.string.weight_text, weight)
    }

    private fun setAge(){
        tvAge.text = age.toString()
    }

    private fun changeComponent() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun getColor(isSelected: Boolean): Int {
        val colorReference = if (isSelected) {
            R.color.md_theme_cardview_selected_background
        } else {
            R.color.md_theme_cardview_NotSelected_background
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun setColor() {
        cvMale.setCardBackgroundColor(getColor(isMaleSelected))
        cvFemale.setCardBackgroundColor(getColor(isFemaleSelected))
    }

}
