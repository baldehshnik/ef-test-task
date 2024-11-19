package com.sparkfusion.ef_test_task.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparkfusion.ef_test_task.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnCourseClickListener {

    private val viewModel: CourseViewModel by viewModels()
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseAdapter = CourseAdapter(this)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = courseAdapter

        lifecycleScope.launch {
            viewModel.courses().collectLatest { pagingData ->
                courseAdapter.submitData(pagingData)
            }
        }
    }

    override fun onCourseClick(courseId: Int) {
        val direction = HomeFragmentDirections.actionHomeFragmentToCourseInfoFragment(courseId)
        findNavController().navigate(direction)
    }
}














