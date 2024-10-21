package com.vu.finalassessment.home.ui

//Import necessary libraries
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vu.finalassessment.R
import com.vu.finalassessment.home.data.ResponseItem
import com.vu.finalassessment.home.recyclerview.MyRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : Fragment() {

    //ViewModel for managing UI-related data.
    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var navigationFunctionLambda: (ResponseItem) -> Unit

    //Adapter for the RecyclerView.
    private lateinit var recyclerViewAdapter: MyRecyclerViewAdapter

    //Called to create the fragment's view.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    //Called after the view has been created.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationFunctionLambda = { responseItem ->
            //Convert List<String> to Array<String> for the officialIn field.
            val officialLanguagesArray = responseItem.officialIn.toTypedArray()

            //Create a Bundle to pass data to the DashboardFragment.
            val bundle = Bundle().apply {
                putString("name", responseItem.name)
                putString("family", responseItem.family)
                putString("branch", responseItem.branch)
                putInt("speakers", responseItem.speakers)
                putString("writingSystem", responseItem.writingSystem)
                putStringArray("officialIn", officialLanguagesArray)
                putString("description", responseItem.description)
            }

            // Navigate to DashboardFragment with the bundle defined above.
            findNavController().navigate(R.id.actionHomeScreenFragmentToDashboardFragment, bundle)
        }

        //Initialize the RecyclerView adapter with navigation.
        recyclerViewAdapter = MyRecyclerViewAdapter(navigationFunction = navigationFunctionLambda)

        //Launch a coroutine to collect and display the greeting text
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.greetingText.collect { greetingTextState ->
                    view.findViewById<TextView>(R.id.screenTitle).text = greetingTextState
                }
            }
        }

        //Launch another coroutine to collect and set the data for the RecyclerView.
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.apiResponseObjects.collect { listOfResponseItems ->
                    //Update the RecyclerView with the list of response items.
                    recyclerViewAdapter.setData(listOfResponseItems)
                }
            }
        }

        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerViewAdapter
    }
}