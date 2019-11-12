package com.example.fuelmanager.DataBaseHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.text.TimeZoneFormat
import com.example.fuelmanager.Model.Refuel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar


class DBHelper(context: Context,
               factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,DB_NAME,
        factory,DB_VERSION) {




    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE =
            ("CREATE TABLE Refuels(refuelID INTEGER PRIMARY KEY NOT NULL , dateTime TEXT, kiloMeter INTEGER, " +
                    "kilometerBetweenRefuel INTEGER, fuelQuantity INTEGER, priceOfRefuel INTEGER)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }


    fun createTable(name:String)
    {
        var db = this.writableDatabase
        var tableCreate="CREATE TABLE $name( ID INTEGER PRIMARY KEY, dateTime TEXT , kiloMeter INTEGER, kilometerBetweenRefuel INTEGER, fuelQuantity INTEGER, priceOfRefuel INTEGER )"
        db.execSQL(tableCreate)

    }


    fun dropTable(name:String)
    {
        var db = this.writableDatabase
        var tableDrop = "DROP TABLE $name"
        db.execSQL(tableDrop)
    }



    //CRUD

    //READ
    fun getRefuels():List<Refuel>
    {

        var refuels = ArrayList<Refuel>()
        val db= this.writableDatabase
        val SELECT_QUERY ="SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(SELECT_QUERY,null)
        if(cursor != null)
        {

            while(cursor.moveToNext())
            {
                var refuel=Refuel("",null,null,null,null)
                refuel.Id = (cursor.getString(cursor.getColumnIndex("refuelID")))
                refuel.dateForRefuelling = cursor.getString( cursor.getColumnIndex(DATETIME))
                refuel.kiloMeter = cursor.getDouble(cursor.getColumnIndex(KILOMETER))
                refuel.kilometerbetweenRefuel = cursor.getDouble(
                    cursor.getColumnIndex(
                        KILOMETERBETWEENREFUEL))
                refuel.fuelQuantity = cursor.getDouble(cursor.getColumnIndex(FUELQUANTITIY))
                refuel.priceOfRefuel = cursor.getInt(cursor.getColumnIndex(PRICEOFREFUEL))

                refuels.add(refuel)
            }
        }
        db.close()
        return refuels
    }

    //CREATE
    fun addRefuel(refuel:Refuel): Boolean {

        val db = this.writableDatabase
        val values = ContentValues()

        values.put(DATETIME, refuel.dateForRefuelling)
        values.put(KILOMETER, refuel.kiloMeter)
        values.put(KILOMETERBETWEENREFUEL, refuel.kilometerbetweenRefuel)
        values.put(FUELQUANTITIY, refuel.fuelQuantity)
        values.put(PRICEOFREFUEL, refuel.priceOfRefuel)

        val _success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$_success") != -1)
    }

    //UPDATE
    fun updateRefuel(refuel:Refuel): Int {
        val db = this.writableDatabase

        // Creating content values
        val values = ContentValues()
        values.put(DATETIME,refuel.dateForRefuelling )
        values.put(KILOMETER, refuel.kiloMeter)
        values.put(KILOMETERBETWEENREFUEL,refuel.kilometerbetweenRefuel )
        values.put(FUELQUANTITIY, refuel.fuelQuantity)
        values.put(PRICEOFREFUEL,refuel.priceOfRefuel )


        return db.update(
            TABLE_NAME, values, "$REFUELID = ?",
            arrayOf(refuel.Id.toString())
        )
    }

    //DELETE
    fun delteRefuel(refuel:Refuel) {
        val db = this.writableDatabase




         db.delete(
        TABLE_NAME,"$REFUELID=?", arrayOf(refuel.Id.toString()))
        db.close()

    }



    fun getRefuel(_id: Int): Refuel {
        var refuel = Refuel("", null, null, null, null)
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            cursor.moveToFirst()
            while (cursor.moveToNext()) {
                var id: Int = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")))
                if (id == _id) {

                    refuel.Id = (cursor.getString(cursor.getColumnIndex("ID")))
                    refuel.dateForRefuelling = cursor.getString(cursor.getColumnIndex(DATETIME))
                    refuel.kiloMeter = cursor.getDouble(cursor.getColumnIndex(KILOMETER))
                    refuel.kilometerbetweenRefuel = cursor.getDouble(
                        cursor.getColumnIndex(
                            KILOMETERBETWEENREFUEL))
                    refuel.fuelQuantity = cursor.getDouble(cursor.getColumnIndex(FUELQUANTITIY))
                    refuel.priceOfRefuel = cursor.getInt(cursor.getColumnIndex(PRICEOFREFUEL))
                }

            }
        }
        cursor.close()
        return refuel
    }








    companion object {

        private val DB_VERSION = 1
        private val DB_NAME = "Refuel_Database"

        private val TABLE_NAME = "Refuels"
        private val REFUELID = "Id"
        private val DATETIME = "dateTime"
        private val KILOMETER = "kiloMeter"
        private val KILOMETERBETWEENREFUEL = "kilometerBetweenRefuel"
        private val FUELQUANTITIY = "fuelQuantity"
        private val PRICEOFREFUEL = "priceOfRefuel"

    }
}

class Column(var name:String,var type:String)