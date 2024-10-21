package com.vu.finalassessment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve arguments passed using Bundle
        val name = arguments?.getString("name") ?: "N/A"
        val family = arguments?.getString("family") ?: "N/A"
        val branch = arguments?.getString("branch") ?: "N/A"
        val speakers = arguments?.getString("speakers") ?: "N/A"
        val writingSystem = arguments?.getString("writingSystem") ?: "N/A"
        val officialIn = arguments?.getStringArray("officialIn")?.joinToString(", ") ?: "N/A"
        val description = arguments?.getString("description") ?: "N/A"

        // Set values for the TextViews using the retrieved arguments
        view.findViewById<TextView>(R.id.nameText).text = "Name: $name"
        view.findViewById<TextView>(R.id.familyText).text = "Family: $family"
        view.findViewById<TextView>(R.id.branchText).text = "Branch: $branch"
        view.findViewById<TextView>(R.id.speakersText).text = "Speaker Number: $speakers"
        view.findViewById<TextView>(R.id.writingSystemText).text = "Writing System: $writingSystem"
        view.findViewById<TextView>(R.id.officialInText).text = "Official Language Of: $officialIn"
        view.findViewById<TextView>(R.id.descriptionText).text = "Description: $description"
    }
}