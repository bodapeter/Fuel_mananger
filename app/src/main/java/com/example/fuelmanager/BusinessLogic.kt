package com.example.fuelmanager

import java.io.File

import kotlin.math.round
import kotlin.math.roundToInt

class BusinessLogic {

    var listOfRefuel = mutableListOf<Refuel>()

    fun AvarageFuel( fueled:Double,  kilometer:Double):Double
    {

        return  (fueled/kilometer)*100
    }
}