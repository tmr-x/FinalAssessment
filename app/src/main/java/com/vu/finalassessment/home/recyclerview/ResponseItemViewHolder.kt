package com.vu.finalassessment.home.recyclerview

//Import necessary libraries
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vu.finalassessment.home.data.ResponseItem
import com.vu.finalassessment.R

class ResponseItemViewHolder(
    //Represents the individual items in the RecyclerView.
    itemView: View,
    private val navigationFunction: (ResponseItem) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    //Initialize TextViews and Button by finding them in the item layout.
    private val nameTextView: TextView = itemView.findViewById(R.id.nameText)
    private val familyTextView: TextView = itemView.findViewById(R.id.familyText)
    private val button: Button = itemView.findViewById(R.id.navigationButton)

    // Binds data from a ResponseItem to the views in the item layout.
    fun bind(item: ResponseItem) {
        //Sets the text for the name and family of the language.
        nameTextView.text = item.name
        familyTextView.text = item.family

        //Set a click listener to trigger navigation when clicked.
        button.setOnClickListener {
            navigationFunction(item)
        }
    }
}