package me.larikraun.tourreviews.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.larikraun.tourreviews.network.ReviewRepository
import me.larikraun.tourreviews.utils.ConnectionUtil

/**
 * Author: Omolara Adejuwon
 * Date: 18/10/2018.
 */
class ReviewViewModelFactory(val repo: ReviewRepository, val connectivity: ConnectionUtil): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReviewViewModel(repo,connectivity) as T
    }
}