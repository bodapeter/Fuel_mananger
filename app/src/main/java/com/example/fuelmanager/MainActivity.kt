package com.example.fuelmanager

import com.example.fuelmanager.DataBaseHelper.DBHelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.database.sqlite.SQLiteDatabase
import android.opengl.Visibility

import android.view.View
import android.widget.*
import com.example.fuelmanager.Model.Refuel
import java.lang.Exception

import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    var databaseHandler : DBHelper = DBHelper(this,null)

    var datalist:List<Refuel> = databaseHandler.getRefuels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //first visitibility
        visibilityInit()


        //add button
        val addButton: Button = findViewById(R.id.btn_add)
        addButton.setOnClickListener()
        {
            addNewRefuel()
            findViewById<View>(R.id.addNewRefuelLayout).visibility = View.INVISIBLE
            findViewById<View>(R.id.showAllRefuel).visibility = View.VISIBLE

        }

        //delete button
        val deleteButton: Button = findViewById(R.id.btn_Delete)

        //update button
        val updateButton: Button = findViewById(R.id.btn_Update)

        //Show all refuel
        val showAllRefuelButton:Button = findViewById(R.id.btn_showAll)
        showAllRefuelButton.setOnClickListener()
        {
            val refuels = databaseHandler.getRefuels()
            val allRefuelListView:ListView = findViewById(R.id.listViewShowAllRefuel)
            allRefuelListView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,refuels)
            findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE

            findViewById<View>(R.id.showAllRefuel).visibility = View.VISIBLE

            /*val refuels = databaseHandler.getRefuels()
            val allRefuelList:ListView = findViewById(R.id.listViewShowAllRefuel)
            allRefuelList.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,refuels)
            showAllRefuelLayout()*/
        }


        //skip button
        val skipnewrefuellayout:Button = findViewById(R.id.btn_new_refuel_layout)
        skipnewrefuellayout.setOnClickListener()
        {
            skipTheAddNewRefuelLayout()
        }

       /* //drop button
        val dropDB:Button = findViewById(R.id.btn_deldatabase)
        dropDB.setOnClickListener()
        {
            dropTable()
        }

*/
        val createDB:Button = findViewById(R.id.btn_createdb)
        createDB.setOnClickListener()
        {
            databaseHandler.createTable("Refuels")
        }



        //back button from show all layout
        val backToMain:Button = findViewById(R.id.btn_backToMain)
        backToMain.setOnClickListener()
        {
            visibilityInit()
        }

        //back button from add layout
        val backToMainFromShowLayout:Button = findViewById(R.id.btn_backToMainFromTheAddLayout)
        backToMainFromShowLayout.setOnClickListener()
        {
            visibilityInit()
        }

        //next to new refuel layout button
       /* fun skipTheAddNewRefuelLayout()
        {
            findViewById<View>(R.id.addNewRefuelLayout).visibility = View.VISIBLE
            findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE
        }*/

    }

    fun addNewRefuel()
    {
        try {


           var refuel = Refuel("",null,null,null,null)


            val dateForRefuel = findViewById<EditText>(R.id.edit_Date).text.toString()
            val kiloMeter = findViewById<EditText>(R.id.edit_KilometerClock).text.toString()
            val kilometerBetweenRefuel = findViewById<EditText>(R.id.edit_KilometersBetweenFillIngUpFuelTank).text.toString()
            val fuelQuantity = findViewById<EditText>(R.id.edit_fuelQuantity).text.toString()
            val priceOfRefuel = findViewById<EditText>(R.id.edit_fuelprice).text.toString()


            refuel.dateForRefuelling = dateForRefuel
            refuel.kiloMeter = kiloMeter.toDouble()
            refuel.kilometerbetweenRefuel = kilometerBetweenRefuel.toDouble()
            refuel.fuelQuantity =  fuelQuantity.toDouble()
            refuel.priceOfRefuel = priceOfRefuel.toInt()



            val thing = databaseHandler.addRefuel(refuel)

           //refuel=databaseHandler.getRefuels().first()
           Toast.makeText(this,"${refuel.toString()} $thing",Toast.LENGTH_LONG).show()
        }
        catch (exp:Exception)
        {
            Toast.makeText(this,exp.toString(),Toast.LENGTH_LONG).show()
        }
    }

    fun dropTable(view:View)
    {
        databaseHandler.dropTable("Refuels")
    }
    fun createTable(view:View)
    {
        databaseHandler.createTable("Refuels")
    }

     fun skipTheAddNewRefuelLayout() {
        findViewById<View>(R.id.addNewRefuelLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE
    }

     fun visibilityInit() {
        findViewById<View>(R.id.addNewRefuelLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.mainLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.showAllRefuel).visibility = View.INVISIBLE
    }

    fun showAllRefuelLayout()
    {
        findViewById<View>(R.id.mainLayout).visibility = View.INVISIBLE
        findViewById<View>(R.id.showAllRefuel).visibility = View.VISIBLE
    }

    fun backToMainLayout()
    {
        findViewById<View>(R.id.addNewRefuelLayout).visibility = View.VISIBLE
        findViewById<View>(R.id.showAllRefuel).visibility = View.INVISIBLE
    }
}
