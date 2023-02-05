//Grace He, 2/5/2023
//the directory says java but this is 100% written in kotlin
package com.example.mobilesoftwareengineeringproject.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mobilesoftwareengineeringproject.R

private val TAG = MainActivity::class.java.name

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //creating app
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //every button can be clicked and will lead to some sort of list
        buttonSetup(findViewById(R.id.button1))
        buttonSetup(findViewById(R.id.button2))
        buttonSetup(findViewById(R.id.button3))
        buttonSetup(findViewById(R.id.button4))
        buttonSetup(findViewById(R.id.button5))
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Entering OnResume")
    }

    //adding functionality to the buttons- press them and they take you to the requested list
    private fun buttonSetup(button: Button) {
        button.setOnClickListener {
            if (button.isPressed) {
                val startListActivity = Intent(this, ListActivity::class.java)
                //adding identification to each button- very important
                startListActivity.putExtra("button", button.id.toString())
                startActivity(startListActivity)
            }
        }
    }

}