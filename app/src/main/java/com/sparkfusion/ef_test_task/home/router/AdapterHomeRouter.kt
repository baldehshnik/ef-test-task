package com.sparkfusion.ef_test_task.home.router

import com.sparkfusion.ef_test_task.navigation.GlobalNavComponentRouter
import com.sparkfusion.features.home.presentation.fragment.HomeFragmentDirections
import com.sparkfusion.features.home.presentation.router.IHomeRouter
import javax.inject.Inject

class AdapterHomeRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : IHomeRouter {

    override fun navigateToDetails(id: Int) {
        globalNavComponentRouter.launch(HomeFragmentDirections.actionHomeFragmentToCourseInfoFragment(id))
    }

}
