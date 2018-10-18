package me.larikraun.tourreviews.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.larikraun.tourreviews.network.ReviewRepository

/**
 * Author: Omolara Adejuwon
 * Date: 18/10/2018.
 */
class ReviewViewModelFactory(val repo: ReviewRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ReviewViewModel(repo) as T
    }
}