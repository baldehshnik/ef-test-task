package com.sparkfusion.features.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sparkfusion.core.R
import com.sparkfusion.core.converter.DateConverter
import com.sparkfusion.core.converter.HtmlConverter
import com.sparkfusion.features.favorite.databinding.ItemSavedCourseBinding
import com.sparkfusion.features.favorite.domain.model.LocalCourseModel
import com.sparkfusion.features.favorite.presentation.OnCourseClickListener

class SavedCoursesAdapter(
    private val listener: OnCourseClickListener
) : ListAdapter<LocalCourseModel, SavedCoursesAdapter.CourseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemSavedCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding, DateConverter(), HtmlConverter())
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course, listener)
    }

    class CourseViewHolder(
        private val binding: ItemSavedCourseBinding,
        private val dateConverter: DateConverter,
        private val htmlConverter: HtmlConverter
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: LocalCourseModel, listener: OnCourseClickListener) {
            binding.titleTextView.text = course.summary
            binding.descriptionTextView.text = htmlConverter.convertHtmlToPlainText(course.description)
            binding.createdTextView.text = dateConverter.convertDateString(course.created)
            binding.priceTextView.text = course.price

            if (course.cover.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(course.cover)
                    .into(binding.courseImageView)
            } else {
                binding.courseImageView.setImageResource(R.drawable.gradient_placeholder)
            }

            binding.notesButton.setOnClickListener {
                listener.onDeleteClick(course.id)
            }

            binding.moreButton.setOnClickListener {
                listener.onCourseClick(course.id)
            }

            binding.root.setOnClickListener {
                listener.onCourseClick(course.id)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalCourseModel>() {
            override fun areItemsTheSame(
                oldItem: LocalCourseModel, newItem: LocalCourseModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LocalCourseModel, newItem: LocalCourseModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}


