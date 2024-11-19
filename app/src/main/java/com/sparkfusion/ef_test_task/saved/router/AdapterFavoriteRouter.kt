package com.sparkfusion.ef_test_task.saved.router

import android.os.Bundle
import com.sparkfusion.ef_test_task.R
import com.sparkfusion.ef_test_task.navigation.GlobalNavComponentRouter
import com.sparkfusion.ef_test_task.utils.COURSE_KEY
import com.sparkfusion.features.favorite.presentation.router.IFavoriteRouter
import javax.inject.Inject

class AdapterFavoriteRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : IFavoriteRouter {

    override fun navigateToCourseDetails(id: Int) {
        val bundle = Bundle().apply {
            putInt(COURSE_KEY, id)
        }
        globalNavComponentRouter.launch(R.id.courseInfoFragment, bundle)
    }
}