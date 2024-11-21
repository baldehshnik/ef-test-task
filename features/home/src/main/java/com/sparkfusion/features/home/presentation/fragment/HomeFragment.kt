package com.sparkfusion.features.home.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparkfusion.features.home.R
import com.sparkfusion.features.home.databinding.FragmentHomeBinding
import com.sparkfusion.features.home.domain.model.CourseModel
import com.sparkfusion.features.home.presentation.OnCourseClickListener
import com.sparkfusion.features.home.presentation.adapter.CourseAdapter
import com.sparkfusion.features.home.presentation.viewmodel.CourseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), OnCourseClickListener {

    private val viewModel: CourseViewModel by viewModels()
    private lateinit var courseAdapter: CourseAdapter
    private var collectJob: Job? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCoursesList(view)
        initButtonsHandlers()
        initSpinner()
    }

    override fun onPause() {
        super.onPause()
        collectJob?.cancel()
    }

    override fun onCourseClick(courseId: Int) {
        viewModel.navigateToDetails(courseId)
    }

    override fun onSaveButtonClick(courseModel: CourseModel) {
        viewModel.changeCourseSaveStatus(courseModel)
    }

    private fun initCoursesList(view: View) {
        courseAdapter = CourseAdapter(this)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = courseAdapter

        collectJob = lifecycleScope.launch {
            viewModel.coursesStateFlow.collectLatest { pagingData ->
                courseAdapter.submitData(pagingData)
            }
        }
    }

    private fun initButtonsHandlers() {
        binding.sortText.setOnClickListener { binding.sortSpinner.performClick() }
        binding.sortButton.setOnClickListener { binding.sortSpinner.performClick() }
    }

    private fun initSpinner() {
        val sortOptions = resources.getStringArray(R.array.sort_options)
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sortOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.sortSpinner.adapter = adapter
        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = parent?.getItemAtPosition(position)?.toString() ?: sortOptions[0]
                if (selectedOption != binding.sortText.text) {
                    viewModel.reloadCourses(selectedOption != sortOptions[0])
                }

                binding.sortText.text = selectedOption
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}