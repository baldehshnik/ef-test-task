package com.sparkfusion.ef_test_task.saved.router

import com.sparkfusion.ef_test_task.navigation.GlobalNavComponentRouter
import com.sparkfusion.features.favorite.presentation.fragment.FavoritesFragmentDirections
import com.sparkfusion.features.favorite.presentation.router.IFavoriteRouter
import javax.inject.Inject

class AdapterFavoriteRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter,
) : IFavoriteRouter {

    override fun navigateToCourseDetails(id: Int) {
        globalNavComponentRouter.launch(FavoritesFragmentDirections.actionFavoritesFragmentToCourseInfoFragment(id))
    }
}