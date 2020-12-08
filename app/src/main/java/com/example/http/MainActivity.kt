 package com.example.http

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnValidarRed.setOnClickListener{
            if(Network.hayRed(this)){
                Toast.makeText(this, "Hay red", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Asegurate de que haya red", Toast.LENGTH_LONG).show()
            }
        }

    }
}