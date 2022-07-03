package com.gotech.questionnaire.views.main.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R

class HeaderAdapter(var show: Boolean) : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return if (show) 1 else 0
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
    }

}