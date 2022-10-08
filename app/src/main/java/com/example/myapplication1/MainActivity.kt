package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gamebttn.setOnClickListener() {
    //if gamebutton is pressed it jump to new activity (gameactivity) to start game
            val intent = Intent(this, gameactivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        aboutbttn.setOnClickListener() {
    //if about bttn is pressed we show a dialog box
            val mview = LayoutInflater.from(this)
                .inflate(R.layout.aboutpopup, null, false)
            //inflate custom layout (aboutpopup), and assign to view variable
            val dialog = MaterialAlertDialogBuilder(this)
                .setView(mview)
                .setCancelable(true)
                .show()//show pop up
            //create dialog box with (mview) as view and display it

        }
    }
}