package com.example.fuelmanager.Model

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


class Refuel(

    var dateForRefuelling:String?,
    var kiloMeter:Double?,
    var kilometerbetweenRefuel:Double?,
    var fuelQuantity:Double?,
    var priceOfRefuel:Int?,
    var Id:String = "")

{




    override fun toString(): String {
        return  " $Id Date: $dateForRefuelling, Kilometer in car: $kiloMeter, KilometerOtherRefuel: $kilometerbetweenRefuel, Quantity of fuel: Price: $fuelQuantity, $priceOfRefuel"
    }



}