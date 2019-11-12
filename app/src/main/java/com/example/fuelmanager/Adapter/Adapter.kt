package com.example.fuelmanager.Adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import com.example.fuelmanager.Model.Refuel
import com.example.fuelmanager.R
import kotlinx.android.synthetic.main.row_layout.view.*


class RefuelAdapter(internal var activity:Activity,
              internal  var lstRefuel:List<Refuel>,
              internal var dateForRefuelling:EditText,
              internal var kiloMeter:EditText,
              internal var kilometerbetweenRefuel:EditText,
              internal var fuelQuantity:EditText,
              internal var priceOfRefuel:EditText
              ):BaseAdapter() {




    internal var inflater:LayoutInflater

    init {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getCount(): Int {
        return lstRefuel.size
    }

    override fun getItem(position: Int): Any {
     return lstRefuel[position]
    }

    override fun getItemId(position: Int): Long {
      return lstRefuel[position].Id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView:View
        rowView = inflater.inflate(R.layout.row_layout,null)

        rowView.txt_row_id.text = lstRefuel[position].Id.toString()
        rowView.txt_row_date_now.text = lstRefuel[position].dateForRefuelling.toString()
        rowView.txt_row_sum_kilometers.text = lstRefuel[position].kiloMeter.toString()
        rowView.txt_row_sum_fuel_price.text = lstRefuel[position].priceOfRefuel.toString()
        rowView.txt_row_sum_fuel_quantity.text = lstRefuel[position].fuelQuantity.toString()

        rowView.setOnClickListener {
            dateForRefuelling.setText(rowView.txt_row_date_now.text.toString())
            kiloMeter.setText(rowView.txt_row_sum_kilometers.text.toString())
            priceOfRefuel.setText(rowView.txt_row_sum_fuel_price.text.toString())
            fuelQuantity.setText(rowView.txt_row_sum_fuel_quantity.text.toString())

        }
        return rowView
    }
}