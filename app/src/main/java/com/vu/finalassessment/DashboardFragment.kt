package com.vu.finalassessment

//Import necessary libraries.
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment.
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Retrieve arguments passed using Bundle.
        val name = arguments?.getString("name") ?: "N/A"
        val family = arguments?.getString("family") ?: "N/A"
        val branch = arguments?.getString("branch") ?: "N/A"
        val speakers = arguments?.getInt("speakers") ?: "N/A"
        val writingSystem = arguments?.getString("writingSystem") ?: "N/A"
        //Transforming the list of items to a String so it can be printed on the DashboardFragment.
        val officialIn = arguments?.getStringArray("officialIn")?.joinToString(", ") ?: "N/A"
        val description = arguments?.getString("description") ?: "N/A"

        //Create and format text with bold titles.
        setBoldTitle(view.findViewById(R.id.nameText), "Name: ", name)
        setBoldTitle(view.findViewById(R.id.familyText), "Family: ", family)
        setBoldTitle(view.findViewById(R.id.branchText), "Branch: ", branch)
        setBoldTitle(view.findViewById(R.id.speakersText), "Speaker Number: ", speakers.toString())
        setBoldTitle(view.findViewById(R.id.writingSystemText), "Writing System: ", writingSystem)
        setBoldTitle(view.findViewById(R.id.officialInText), "Official Language Of: ", officialIn)
        setBoldTitle(view.findViewById(R.id.descriptionText), "Description: ", description)
    }

    //Set the text with a bold title, and normal content.
    private fun setBoldTitle(textView: TextView, title: String, content: String) {
        val spannableTitle = SpannableString("$title$content")
        spannableTitle.setSpan(
            StyleSpan(Typeface.BOLD), //Apply bold style.
            0, title.length, //Bold the title only.
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = spannableTitle
    }
}