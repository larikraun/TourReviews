package me.larikraun.tourreviews;

import android.app.Application
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Author: Omolara Adejuwon
 * Date: 17/10/2018.
 */
class TourReviewApp : Application() {

	override fun onCreate() {
		super.onCreate ()
		CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
				.setDefaultFontPath("fonts/montserrat/Montserrat-Regular.otf")
				.setFontAttrId(R.attr.fontPath)
				.build()
		)
	}
}
