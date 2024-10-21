package com.vu.finalassessment.home.ui

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
import kotlinx.coroutines.launch

class HomeScreenFragment : Fragment() {

    private val viewModel: HomeScreenViewModel by viewModels()
    private lateinit var navigationFunctionLambda: (ResponseItem) -> Unit
    private lateinit var recyclerViewAdapter: MyRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the navigation function lambda to pass only the necessary arguments to DashboardFragment
        navigationFunctionLambda = { responseItem ->
            // Convert List<String> to Array<String> for officialIn
            val officialLanguagesArray = responseItem.officialIn.toTypedArray()

            val bundle = Bundle().apply {
                putString("name", responseItem.name)
                putString("family", responseItem.family)
                putString("branch", responseItem.branch)
                putInt("speakers", responseItem.speakers)  // Pass speakers as an integer
                putString("writingSystem", responseItem.writingSystem)
                putStringArray("officialIn", officialLanguagesArray)
                putString("description", responseItem.description)
            }

            findNavController().navigate(R.id.actionHomeScreenFragmentToDashboardFragment, bundle)
        }

        recyclerViewAdapter = MyRecyclerViewAdapter(navigationFunction = navigationFunctionLambda)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.greetingText.collect { greetingTextState ->
                    view.findViewById<TextView>(R.id.screenTitle).text = greetingTextState
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.apiResponseObjects.collect { listOfResponseItems ->
                    recyclerViewAdapter.setData(listOfResponseItems)
                }
            }
        }

        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = recyclerViewAdapter
    }
}