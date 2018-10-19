package me.larikraun.tourreviews.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import me.larikraun.tourreviews.model.Review
import me.larikraun.tourreviews.model.ReviewResponse
import me.larikraun.tourreviews.network.ReviewRepository
import me.larikraun.tourreviews.utils.ConnectionUtil
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.TimeUnit


/**
 * Author: Omolara Adejuwon
 * Date: 19/10/2018.
 */
class ReviewViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    private var repository: ReviewRepository = mock()
    private var connectivity: ConnectionUtil = mock()
    private var viewModel = ReviewViewModel(repository, connectivity)

    @Test
    fun `display UI when there is an error`() {
        viewModel.updateUIForWithError(Throwable("No Internet connection"))
        assertEquals("No Internet connection", viewModel.errorMessage.value!!.message)
    }

    @Test
    fun `display recycler view when api returns a valid response`() {
        val response = ReviewResponse()
        response.status = true
        response.totalReviewsComments = 10
        val reviews = ArrayList<Review>()
        reviews.add(Review(type = "REVIEW", rating = "3.5f", title = "A random title", message = "A very long and random message from the author", author = "Omolara - Nigeria", date = "October, 19, 2018"))
        response.reviews = reviews
        whenever(repository.fetchReviews(2, 0))
                .thenReturn(Observable.just(response))

        val subscriber = TestObserver<ReviewResponse>().apply {
            repository.fetchReviews(2, 0).subscribe(this)
            this.awaitTerminalEvent(2, TimeUnit.SECONDS)
            this.assertNoErrors()
        }
        val onSuccess = subscriber.events
        val reviewResponse = onSuccess[0]
        assertEquals(true, (reviewResponse[0] as ReviewResponse).status)
        assertEquals(10, (reviewResponse[0] as ReviewResponse).totalReviewsComments)

    }

    @Test
    fun `display error when the api is not successful`() {
        val errorResponse = Throwable("Error Occurred")
        whenever(repository.fetchReviews(2, 0))
                .thenReturn(Observable.error(errorResponse))
        repository.fetchReviews(2, 0).test().assertError(errorResponse)

    }
}