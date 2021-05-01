package com.cognitus.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BtnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /*val button1=findViewById<Button>(R.id.btn1)

        button1.setOnClickListener {
            //Toast.makeText(this, "Hola dio clic al botón", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("valor","A125")
            startActivity(intent)
        }*/
    }
}