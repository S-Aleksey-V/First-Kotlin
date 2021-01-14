package me.tolkstudio.firstkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val button = findViewById<Button>(R.id.push_button)

      val textView = findViewById<TextView>(R.id.first_lesson)

        button.setOnClickListener {
            (it as Button).text = "Next Lesson"
            textView.text = "14.01.2021"
            
        }
    }
}