package com.sparkfusion.ef_test_task.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sparkfusion.data.entity.CourseEntity
import com.sparkfusion.ef_test_task.databinding.ItemCourseBinding
import com.sparkfusion.core.R as CoreResources

class CourseAdapter(
    private val listener: OnCourseClickListener
) :
    PagingDataAdapter<CourseEntity, CourseAdapter.CourseViewHolder>(DIFF_CALLBACK) {

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
        fun bind(course: CourseEntity?, listener: OnCourseClickListener) {
            binding.titleTextView.text = course?.summary ?: "-"
            binding.descriptionTextView.text = course?.description ?: "-"
            binding.createdTextView.text = "Created: ${course?.created ?: "-"}"
            binding.priceTextView.text = "Price: ${course?.price ?: "-"}"

            if (!course?.cover.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(course?.cover)
                    .into(binding.courseImageView)
            } else {
                binding.courseImageView.setImageResource(CoreResources.drawable.gradient_placeholder)
            }

            binding.root.setOnClickListener {
                course?.id?.let { id ->
                    listener.onCourseClick(id)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseEntity>() {

            override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}















