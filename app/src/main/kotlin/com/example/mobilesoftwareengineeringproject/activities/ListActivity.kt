//Grace He, 2/5/2023
//the directory says java but this is 100% written in kotlin
package com.example.mobilesoftwareengineeringproject.activities

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilesoftwareengineeringproject.R
import com.example.mobilesoftwareengineeringproject.models.Item
import com.example.mobilesoftwareengineeringproject.network.Client
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//all of the list displays are consolidated here
class ListActivity: AppCompatActivity() {
    //coroutine function for networking despite it being disallowed on the main path - essentially
    //  mashes the two functions from Client together and does the processes contained within them.
    //also note that because of this, it's a bit slow when it first gets booted up, but it does work
    private suspend fun getBigListFromNetwork():List<List<Item>> {
        delay(0L)
        return Client.separateData(Client.getData())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //creating the list page and ensuring that you can go back
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val returnToMain = Intent(this, MainActivity::class.java)
        returnToMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK + Intent.FLAG_ACTIVITY_NEW_TASK

        //coroutine function part two electric boogaloo
        GlobalScope.launch {
            //bigList contains all of the lists
            val bigList = getBigListFromNetwork()
            val listDisplay = findViewById<TextView>(R.id.textView2)
            var str: String
            //depending on what button was pressed before, this part takes a certain list stored in
            //  bigList and adds the names all into one string
            when (intent.getStringExtra("button")) {
                R.id.button1.toString() -> {
                    str = "List 1: \n\n"
                    for (item in bigList[1]) {
                        str +="${item.name}\n"
                    }
                }
                R.id.button2.toString() -> {
                    str = "List 2: \n\n"
                    for (item in bigList[2]) {
                        str += "${item.name}\n"
                    }
                }
                R.id.button3.toString() -> {
                    str = "List 3: \n\n"
                    for (item in bigList[3]) {
                        str += "${item.name}\n"
                    }
                }
                R.id.button4.toString() -> {
                    str = "List 4: \n\n"
                    for (item in bigList[4]) {
                        str += "${item.name}\n"
                    }
                }
                else -> {
                    str = "List 1: \n\n"
                    for (item in bigList[1]) {
                        str += "${item.name}\n"
                    }
                    str += "\n\nList 2: \n\n"
                    for (item in bigList[2]) {
                        str += "${item.name}\n"
                    }
                    str += "\n\nList 3: \n\n"
                    for (item in bigList[3]) {
                        str += "${item.name}\n"
                    }
                    str += "\n\nList 4: \n\n"
                    for (item in bigList[4]) {
                        str += "${item.name}\n"
                    }
                }
            }

            //this is the stuff that updates the UI, and here we add a scroll wheel and the text
            //  from the string that was just made.
            runOnUiThread {
                listDisplay.movementMethod = ScrollingMovementMethod()
                listDisplay.text = str
            }

        }


    }
}