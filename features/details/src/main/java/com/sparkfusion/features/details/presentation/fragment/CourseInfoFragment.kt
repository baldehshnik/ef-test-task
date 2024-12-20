package com.sparkfusion.features.details.presentation.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sparkfusion.core.converter.DateConverter
import com.sparkfusion.core.converter.HtmlConverter
import com.sparkfusion.features.details.R
import com.sparkfusion.features.details.databinding.FragmentCourseInfoBinding
import com.sparkfusion.features.details.domain.model.AuthorModel
import com.sparkfusion.features.details.presentation.adapter.AuthorsAdapter
import com.sparkfusion.features.details.presentation.viewmodel.CourseInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseInfoFragment : Fragment() {

    private val viewModel: CourseInfoViewModel by viewModels()

    private lateinit var binding: FragmentCourseInfoBinding

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

        val courseId = arguments?.getInt("courseId")
        if (courseId != null) viewModel.readCourse(courseId)
        else {
            Toast.makeText(requireContext(), "Not found", Toast.LENGTH_SHORT).show()
        }

        val dateConverter = DateConverter()
        val htmlConverter = HtmlConverter()
        viewModel.course.observe(viewLifecycleOwner) { course ->
            binding.startCourseButton.setOnClickListener { navigateToLink(course.canonicalUrl) }

            binding.titleTextView.text = course.summary
            binding.createdTextView.text = dateConverter.convertDateString(course.created)
            binding.descriptionTextView.text = htmlConverter.convertHtmlToPlainText(course.description)

            if (course.isSaved) {
                binding.notesButton.setImageResource(R.drawable.ic_filled_bookmark)
            } else {
                binding.notesButton.setImageResource(R.drawable.ic_bookmark)
            }

            Glide.with(this)
                .load(course.cover)
                .into(binding.courseImageView)
        }

        viewModel.authors.observe(viewLifecycleOwner) { observeAuthors(it) }

        binding.notesButton.setOnClickListener { viewModel.changeCourseSaveStatus() }
        binding.goToPlatformButton.setOnClickListener { navigateToLink(STEPIK_LINK) }
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
    }

    private fun observeAuthors(authors: List<AuthorModel>) {
        val authorsAdapter = AuthorsAdapter(authors)
        binding.usersRecyclerView.adapter = authorsAdapter
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun navigateToLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(link)
        }
        startActivity(intent)
    }

    companion object {
        private const val STEPIK_LINK = "https://stepik.org"
    }
}





























