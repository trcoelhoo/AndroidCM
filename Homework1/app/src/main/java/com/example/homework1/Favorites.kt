package com.example.homework1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.homework1.databinding.ActivityFavoritesBinding

class Favorites : AppCompatActivity() {


    // second activity to change the favorite contact opened
    // initialize the buttons 
    private lateinit var button: Button
    //initialize the text views
    private lateinit var nametv: EditText
    private lateinit var numbertv: EditText
    //receive the name and number from the first activity
    private var name: String = ""
    private var number: String = ""
    //initialize the string to store the new name and number
    private var newname: String = ""
    private var newnumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the name and number from the first activity
        name = intent.getStringExtra("name").toString()
        number = intent.getStringExtra("number").toString()

        //initialize the buttons
        button = findViewById(R.id.button)
        //initialize the text views
        nametv = findViewById(R.id.nametv)
        numbertv = findViewById(R.id.numbertv)

        //set the name and number to the text views
        nametv.setText(name)
        numbertv.setText(number)

        //set the button to change the name and number
        button.setOnClickListener {
            //get the new name and number
            newname = nametv.text.toString()
            newnumber = numbertv.text.toString()
            //send the new name and number to the first activity
            val intent = Intent()
            intent.putExtra("newname", newname)
            intent.putExtra("newnumber", newnumber)
            setResult(RESULT_OK, intent)
            finish()
        }
    }


}