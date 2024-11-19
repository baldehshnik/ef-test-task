package com.sparkfusion.ef_test_task.course.router

import com.sparkfusion.ef_test_task.navigation.GlobalNavComponentRouter
import com.sparkfusion.features.details.presentation.router.IDetailsRouter
import javax.inject.Inject

class AdapterDetailsRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : IDetailsRouter {


}