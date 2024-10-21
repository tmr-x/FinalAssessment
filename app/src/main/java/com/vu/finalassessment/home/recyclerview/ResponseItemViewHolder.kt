package com.vu.finalassessment.home.recyclerview

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vu.finalassessment.home.data.ResponseItem
import com.vu.finalassessment.R

class ResponseItemViewHolder(
    itemView: View,
    private val navigationFunction: (ResponseItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val nameTextView: TextView = itemView.findViewById(R.id.nameText)
    private val familyTextView: TextView = itemView.findViewById(R.id.familyText)
    private val button: Button = itemView.findViewById(R.id.navigationButton)

    fun bind(item: ResponseItem) {
        nameTextView.text = item.name
        familyTextView.text = item.family

        // Set the button click listener to trigger navigation
        button.setOnClickListener {
            navigationFunction(item)
        }
    }
}