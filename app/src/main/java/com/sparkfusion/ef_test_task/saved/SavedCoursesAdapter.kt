package com.sparkfusion.ef_test_task.saved

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sparkfusion.core.R
import com.sparkfusion.data.entity.LocalCourseDataEntity
import com.sparkfusion.ef_test_task.databinding.ItemCourseBinding
import com.sparkfusion.ef_test_task.main.OnCourseClickListener

class SavedCoursesAdapter(
    private val listener: OnCourseClickListener
) : ListAdapter<LocalCourseDataEntity, SavedCoursesAdapter.CourseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course, listener)
    }

    class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(course: LocalCourseDataEntity, listener: OnCourseClickListener) {
            binding.titleTextView.text = course.summary
            binding.descriptionTextView.text = course.description
            binding.createdTextView.text = "Created: ${course.created}"
            binding.priceTextView.text = "Price: ${course.price}"

            if (course.cover.isNotEmpty()) {
                Glide.with(binding.root.context)
                    .load(course.cover)
                    .into(binding.courseImageView)
            } else {
                binding.courseImageView.setImageResource(R.drawable.gradient_placeholder)
            }

            binding.root.setOnClickListener {
                listener.onCourseClick(course.id)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalCourseDataEntity>() {
            override fun areItemsTheSame(
                oldItem: LocalCourseDataEntity,
                newItem: LocalCourseDataEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: LocalCourseDataEntity,
                newItem: LocalCourseDataEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}


