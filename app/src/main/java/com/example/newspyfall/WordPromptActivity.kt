package com.example.newspyfall

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WordPromptActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_prompt)

        val userNameTextView: TextView = findViewById(R.id.userNameTextView)
        val locationTextView: TextView  = findViewById(R.id.locationTextView)
        val understoodButton: Button = findViewById(R.id.understoodButton)
        val showButton: Button = findViewById(R.id.showButton)

        val userName = intent.getStringExtra("userName")
        val location = intent.getStringExtra("location")

        userNameTextView.text = userName
        locationTextView.text = location

        showButton.setOnClickListener {
            locationTextView.visibility = View.VISIBLE
            showButton.visibility = View.GONE
            understoodButton.visibility = View.VISIBLE
        }

        understoodButton.setOnClickListener {
            finish()
        }




    }
}
