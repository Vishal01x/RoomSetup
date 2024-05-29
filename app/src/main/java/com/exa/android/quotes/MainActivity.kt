package com.exa.android.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

/*
  To use room database , we need to add some dependency see this website:
  https://developer.android.com/training/data-storage/room

  In case you don't understand, what this series of 3 (10, 11, 12) videos : https://youtu.be/yPL13Iwy6oM?si=CHrfs5aQQo-1AAax
 */


/* update-1:
class MainActivity : AppCompatActivity() {
    private lateinit var database:ContactDatabase
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database= Room.databaseBuilder(applicationContext,
            ContactDatabase::class.java,
            "contactDB").build()
        database= ContactDatabase.getDatabase(this)
        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "John", "000000", Date())
        }
        val btn=findViewById<TextView>(R.id.btwn)
        btn.setOnClickListener {
            database.contactDao().getContact().observe(this) {
                Log.d("databaseEntry: ", it.toString())
            }
        }
        /*
        * you can see in the "App Inspection" near Terminal or LogCat that the entry is inserted in Database Inspection
        * you can also see all the data in logcat after click on textview.After click on textview , in LogCat search the Log tag "databaseEntry: " as you use in filter bar, you will see the rows.
        */
    }
}
*/
// update -2:
class MainActivity : AppCompatActivity() {

    lateinit var database : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         database = UserDatabase.getDatabase(applicationContext)

        // Create a SimpleDateFormat instance to parse the date string
        val dateFormat = SimpleDateFormat("dd MMM yyyy")

        // Parse the date string "5 Jan 2016" into a Date object
        val birthDate = dateFormat.parse("5 Jan 2016")

        GlobalScope.launch {
            database.userDao().insertData(User(5,"Vishal", "Dangi", Date(),1))
        }

    }
}