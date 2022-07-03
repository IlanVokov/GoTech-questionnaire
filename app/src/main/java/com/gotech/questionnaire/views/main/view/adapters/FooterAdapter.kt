package com.gotech.questionnaire.views.main.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.gotech.questionnaire.R

class FooterAdapter(var show: Boolean, val onSubmitClickListener: View.OnClickListener) : RecyclerView.Adapter<FooterAdapter.FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_footer, parent, false)
        return FooterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return if (show) 1 else 0
    }

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val submitButton: Button = view
            .findViewById(R.id.submitButton)

        fun bind() {
            submitButton.setOnClickListener(onSubmitClickListener)
        }
    }

}