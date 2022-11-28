package com.example.homework1

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import android.widget.TextView
import com.karumi.dexter.*
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    //initializ buttons
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var b4: Button
    private lateinit var b5: Button
    private lateinit var b6: Button
    private lateinit var b7: Button
    private lateinit var b8: Button
    private lateinit var b9: Button
    private lateinit var b0: Button
    private lateinit var bhash: Button
    private lateinit var bplus: Button

    private lateinit var bfav1: Button
    private lateinit var bfav2: Button
    private lateinit var bfav3: Button
    //image button
    private lateinit var back: ImageButton
    private lateinit var bcall: ImageButton
    //text view
    private lateinit var number: TextView
    //string to store number
    private var num: String = ""
    //string to store fav numbers and names
    private var fav1number: String = ""
    private var fav2number: String = ""
    private var fav3number: String = ""
    private var fav1name: String = ""
    private var fav2name: String = ""
    private var fav3name: String = ""
    private var fav1bool: Boolean=false
    private var fav2bool: Boolean=false
    private var fav3bool: Boolean=false

    //string to store number to be called
    private var call: String = ""
    //string to store number to be added to fav
    private var add: String = ""
    //string to store number to be added to fav
    private var addname: String = ""
    //string to store number to be deleted from fav
    private var delete: String = ""
    //string to store number to be deleted from fav
    private var deletename: String = ""


    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
        Log.d("TAG", "received")
        Log.d("TAG", uri.toString())
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize buttons
        b1=findViewById<Button>(R.id.b1)
        b2=findViewById<Button>(R.id.b2)
        b3=findViewById<Button>(R.id.b3)
        b4=findViewById<Button>(R.id.b4)
        b5=findViewById<Button>(R.id.b5)
        b6=findViewById<Button>(R.id.b6)
        b7=findViewById<Button>(R.id.b7)
        b8=findViewById<Button>(R.id.b8)
        b9=findViewById<Button>(R.id.b9)
        b0=findViewById<Button>(R.id.b0)
        bhash=findViewById<Button>(R.id.bhash)
        bplus=findViewById<Button>(R.id.bplus)
        bcall=findViewById<ImageButton>(R.id.bcall)
        bfav1=findViewById<Button>(R.id.bf1)
        bfav2=findViewById<Button>(R.id.bf2)
        bfav3=findViewById<Button>(R.id.bf3)
        //initialize image button
        back=findViewById<ImageButton>(R.id.bclear)
        //text view
        number=findViewById<TextView>(R.id.tv)

        Dexter.withContext(this).withPermission(Manifest.permission.CALL_PHONE).withListener(object :
            PermissionListener {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                //permission granted

            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                //permission denied
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken?) {
                //permission denied
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }

        }).check()

        //set onclick listener for buttons
        b1.setOnClickListener {
            num += "1"
            number.text = num
        }
        b2.setOnClickListener {
            num += "2"
            number.text = num
        }
        b3.setOnClickListener {
            num += "3"
            number.text = num
        }
        b4.setOnClickListener {
            num += "4"
            number.text = num
        }
        b5.setOnClickListener {
            num += "5"
            number.text = num
        }
        b6.setOnClickListener {
            num += "6"
            number.text = num
        }
        b7.setOnClickListener {
            num += "7"
            number.text = num
        }
        b8.setOnClickListener {
            num += "8"
            number.text = num
        }
        b9.setOnClickListener {
            num += "9"
            number.text = num
        }
        b0.setOnClickListener {
            num += "0"
            number.text = num
        }
        bhash.setOnClickListener {
            num += "#"
            number.text = num
        }
        bplus.setOnClickListener {
            num += "+"
            number.text = num
        }
        back.setOnClickListener {
            num = num.dropLast(1)
            number.text = num
        }
        bcall.setOnClickListener {
            //call fun to call number
            call = num
            call(call)
        }
        // fav1 onclick call
        bfav1.setOnClickListener {
            Log.d("TAG", fav1number)
            if (fav1number==""){
                //alert dialog to long press to add number to fav
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Don't have a fav")
                builder.setMessage("Long press to add number to fav")
                builder.setPositiveButton("OK"){dialog, which ->
                    //do nothing
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
                
                
            }
            else {
                call = fav1number.toString()
                call(call)
            }

        }
        // fav1 onlongpress send to second activity and send fav1 number and name
        bfav1.setOnLongClickListener {

            fav1bool=true
            fav1name = fav1name
            fav1number = fav1number
            val intent = Intent(this, Favorites::class.java)
            intent.putExtra("name", fav1name)
            intent.putExtra("number", fav1number)
            startActivityForResult(intent, 1)

            true
        }
        // fav2 onclick call
        bfav2.setOnClickListener {
            if (fav2number==""){
                //alert dialog to long press to add number to fav
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Don't have a fav")
                builder.setMessage("Long press to add number to fav")
                builder.setPositiveButton("OK"){dialog, which ->
                    //do nothing
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()


            }
            else {
                call = fav2number.toString()
                call(call)
            }
        }
        bfav2.setOnLongClickListener {

            fav2bool=true
            fav2name = fav2name
            fav2number = fav2number
            val intent = Intent(this, Favorites::class.java)
            intent.putExtra("name", fav2name)
            intent.putExtra("number", fav2number)
            startActivityForResult(intent, 1)

            true
        }
        // fav3 onclick call
        bfav3.setOnClickListener {
            if (fav3number==""){
                //alert dialog to long press to add number to fav
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Don't have a fav")
                builder.setMessage("Long press to add number to fav")
                builder.setPositiveButton("OK"){dialog, which ->
                    //do nothing
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()


            }
            else {
                call = fav3number.toString()
                call(call)
            }
        }
        bfav3.setOnLongClickListener {

            fav3bool=true
            fav3name = fav3name
            fav3number = fav3number
            val intent = Intent(this, Favorites::class.java)
            intent.putExtra("name", fav3name)
            intent.putExtra("number", fav3number)
            startActivityForResult(intent, 1)

            true
        }



        //get result from second activity

    }
    //make phone call
    fun call(view: String ) {
        call = view
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + call))
        startActivity(intent)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("TAG", "received")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                val result = data?.getStringExtra("newname")
                val result2 = data?.getStringExtra("newnumber")
                if (result != null) {
                    if (result2 != null) {
                        Log.d("TAG", fav1bool.toString())
                    }
                }
                if (fav1bool) {

                    fav1bool=false
                    bfav1.text = result
                    if (result != null) {
                        fav1name = result
                    }
                    if (result2 != null) {
                        fav1number = result2
                    }
                }
                if (fav2bool) {

                    fav2bool=false
                    bfav2.text = result
                    if (result != null) {
                        fav2name = result
                    }
                    if (result2 != null) {
                        fav2number = result2
                    }
                }
                if (fav3bool) {

                    fav3bool=false
                    bfav3.text = result
                    if (result != null) {
                        fav3name = result
                    }
                    if (result2 != null) {
                        fav3number = result2
                    }
                }

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }



    
}


