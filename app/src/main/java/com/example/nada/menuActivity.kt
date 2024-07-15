package com.example.nada

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.nada.imcapp.ImcAppActivity


class menuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnImcApp = findViewById<AppCompatButton>(R.id.btnImcApp)

        btnImcApp.setOnClickListener{ navigateToImcAppActivity()}
    }
    private fun navigateToImcAppActivity() {
        val intent = Intent(this, ImcAppActivity::class.java)
        startActivity(intent)
    }

}