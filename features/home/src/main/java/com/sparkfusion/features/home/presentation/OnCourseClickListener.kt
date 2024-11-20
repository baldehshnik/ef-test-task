package com.sparkfusion.features.home.presentation

import com.sparkfusion.features.home.domain.model.CourseModel

interface OnCourseClickListener {
    fun onCourseClick(courseId: Int)
    fun onSaveButtonClick(courseModel: CourseModel)
}
