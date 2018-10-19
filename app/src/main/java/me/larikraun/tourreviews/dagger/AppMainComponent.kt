package me.larikraun.tourreviews.dagger

import dagger.Component
import me.larikraun.tourreviews.ui.ReviewActivity

@Component(modules = [(ContextModule::class), (RepositoryModule::class)])
interface AppMainComponent {
    fun inject(activity: ReviewActivity)
}
