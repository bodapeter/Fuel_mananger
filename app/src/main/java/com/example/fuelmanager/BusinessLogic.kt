package com.example.fuelmanager

import java.io.File



class BusinessLogic {

    var listOfRefuel = mutableListOf<Refuel>()

    fun AvarageFuel( fueled:Double,  kilometer:Double):Double
    {

        return  (fueled/kilometer)*100
    }
}