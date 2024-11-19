package com.sparkfusion.ef_test_task.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sparkfusion.ef_test_task.databinding.FragmentCourseInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseInfoFragment : Fragment() {

    private val viewModel: CourseInfoViewModel by viewModels()

    private lateinit var binding: FragmentCourseInfoBinding
    private val args: CourseInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourseInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courseId = args.courseId
        viewModel.readCourse(courseId)

        viewModel.course.observe(viewLifecycleOwner) { course ->
            binding.titleTextView.text = course.summary
            binding.creationDateTextView.text = course.created
            binding.descriptionTextView.text = course.description

            Glide.with(this)
                .load(course.cover)
                .into(binding.courseImageView)
        }

        viewModel.authors.observe(viewLifecycleOwner) { authors ->
            val authorsAdapter = AuthorsAdapter(authors)
            binding.usersRecyclerView.adapter = authorsAdapter
            binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.notesButton.setOnClickListener {
            viewModel.saveCourse()
        }

        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }
}





























