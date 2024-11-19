package com.sparkfusion.ef_test_task.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparkfusion.ef_test_task.R
import com.sparkfusion.ef_test_task.home.OnCourseClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), OnCourseClickListener {

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.readCourses()

        val courseAdapter = SavedCoursesAdapter(this)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = courseAdapter

        viewModel.courses.observe(viewLifecycleOwner) { courses ->
            courseAdapter.submitList(courses)
        }
    }

    override fun onCourseClick(courseId: Int) {
        val direction = FavoritesFragmentDirections.actionFavoritesFragmentToCourseInfoFragment(courseId)
        findNavController().navigate(direction)
    }
}























