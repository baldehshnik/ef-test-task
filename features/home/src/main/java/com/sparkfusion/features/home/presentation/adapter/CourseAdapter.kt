package com.sparkfusion.features.home.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sparkfusion.features.home.databinding.ItemCourseBinding
import com.sparkfusion.features.home.domain.model.CourseModel
import com.sparkfusion.features.home.presentation.OnCourseClickListener
import com.sparkfusion.core.R as CoreResources

class CourseAdapter(
    private val listener: OnCourseClickListener
) :
    PagingDataAdapter<CourseModel, CourseAdapter.CourseViewHolder>(DIFF_CALLBACK) {

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
        fun bind(course: CourseModel?, listener: OnCourseClickListener) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseModel>() {

            override fun areItemsTheSame(oldItem: CourseModel, newItem: CourseModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CourseModel, newItem: CourseModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}















