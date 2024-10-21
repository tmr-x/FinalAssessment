package com.vu.finalassessment.home.recyclerview

//Import necessary libraries
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vu.finalassessment.R
import com.vu.finalassessment.home.data.ResponseItem

//Custom RecyclerView adapter
class MyRecyclerViewAdapter(
    //Stores the list of ResponseItem objects that will be displayed in the RecyclerView
    private val dataList: MutableList<ResponseItem> = mutableListOf(),
    private val navigationFunction: (ResponseItem) -> Unit
) : RecyclerView.Adapter<ResponseItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_restful_api_dev, parent, false)
        return ResponseItemViewHolder(view, navigationFunction)
    }

    //Binds data to the ViewHolder
    override fun onBindViewHolder(viewHolder: ResponseItemViewHolder, position: Int) {
        viewHolder.bind(dataList[position])
    }

    // Returns the total number of items in the dataList
    override fun getItemCount() = dataList.size

    //Updates the adapter's data with a new list and refreshes the RecyclerView
    fun setData(newDataList: List<ResponseItem>) {
        dataList.clear()
        dataList.addAll(newDataList)
        notifyDataSetChanged()
    }
}