package com.sparkfusion.ef_test_task.home.router

import android.os.Bundle
import com.sparkfusion.ef_test_task.R
import com.sparkfusion.ef_test_task.navigation.GlobalNavComponentRouter
import com.sparkfusion.ef_test_task.utils.COURSE_KEY
import com.sparkfusion.features.home.presentation.router.IHomeRouter
import javax.inject.Inject

class AdapterHomeRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : IHomeRouter {

    override fun navigateToDetails(id: Int) {
        val bundle = Bundle().apply {
            putInt(COURSE_KEY, id)
        }
        globalNavComponentRouter.launch(R.id.courseInfoFragment, bundle)
    }

}
