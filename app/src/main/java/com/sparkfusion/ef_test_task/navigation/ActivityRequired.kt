package com.sparkfusion.ef_test_task.navigation

import androidx.fragment.app.FragmentActivity

interface ActivityRequired {

    fun onCreated(activity: FragmentActivity)

    fun onDestroyed()

}
