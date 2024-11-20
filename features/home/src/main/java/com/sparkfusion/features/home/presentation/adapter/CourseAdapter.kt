package com.sparkfusion.features.home.presentation.adapter

import android.graphics.PorterDuff
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sparkfusion.core.converter.DateConverter
import com.sparkfusion.core.converter.HtmlConverter
import com.sparkfusion.features.home.R
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
        return CourseViewHolder(binding, DateConverter(), HtmlConverter())
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course, listener)
    }

    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val dateConverter: DateConverter,
        private val htmlConverter: HtmlConverter
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: CourseModel?, listener: OnCourseClickListener) {
            binding.titleTextView.text = course?.summary ?: "-"
            binding.descriptionTextView.text = course?.description?.let { htmlConverter.convertHtmlToPlainText(it) } ?: "-"
            binding.createdTextView.text = course?.created?.let { dateConverter.convertDateString(it) } ?: "-"
            binding.priceTextView.text = course?.price ?: "FREE"

            if (course?.isSaved == true) {
                binding.notesButton.setImageResource(R.drawable.ic_filled_bookmark)
                binding.notesButton.setColorFilter(getPrimaryColor(), PorterDuff.Mode.SRC_IN)
            } else {
                binding.notesButton.setImageResource(R.drawable.ic_bookmark)
                binding.notesButton.imageTintList = null
            }

            if (!course?.cover.isNullOrEmpty()) {
                Glide.with(binding.root.context)
                    .load(course?.cover)
                    .into(binding.courseImageView)
            } else {
                binding.courseImageView.setImageResource(CoreResources.drawable.gradient_placeholder)
            }

            binding.notesButton.setOnClickListener {
                course?.let { listener.onSaveButtonClick(it) }
            }

            binding.root.setOnClickListener {
                course?.id?.let { id ->
                    listener.onCourseClick(id)
                }
            }
        }

        private fun getPrimaryColor(): Int {
            val typedValue = TypedValue()
            binding.root.context.theme.resolveAttribute(
                androidx.appcompat.R.attr.colorPrimary, typedValue, true
            )
            return typedValue.data
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















