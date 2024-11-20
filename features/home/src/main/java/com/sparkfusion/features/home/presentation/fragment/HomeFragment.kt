package com.sparkfusion.features.home.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparkfusion.features.home.R
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

        collectJob = lifecycleScope.launch {
            viewModel.coursesStateFlow.collectLatest { pagingData ->
                courseAdapter.submitData(pagingData)
            }
        }
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
}