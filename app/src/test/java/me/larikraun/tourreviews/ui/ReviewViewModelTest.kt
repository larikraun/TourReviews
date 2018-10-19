package me.larikraun.tourreviews.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import me.larikraun.tourreviews.model.ReviewResponse
import me.larikraun.tourreviews.network.ReviewRepository
import me.larikraun.tourreviews.utils.ConnectionUtil
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.anyInt
import java.util.concurrent.TimeUnit


/**
 * Author: Omolara Adejuwon
 * Date: 19/10/2018.
 */
class ReviewViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()
    var repository: ReviewRepository = mock()
    var connectivity: ConnectionUtil = mock()
    var viewModel = ReviewViewModel(repository, connectivity)

    @Test
    fun `display UI when there is an error`() {
        viewModel.updateUIForWithError(Throwable("No Internet connection"))
        assertEquals("No Internet connection", viewModel.errorMessage.value!!.message)
    }

    @Test
    fun `display recycler view when api returns a valid response`() {
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
        whenever(repository.fetchReviews(2, 0))
                .thenReturn(Observable.error(Throwable("Error Occurred")))
        //   val subscriber = TestObserver<ReviewResponse>().apply {
        repository.fetchReviews(anyInt(), anyInt()).test().assertError(Throwable("Error occurred"))
//                    .subscribe(this)
//            this.awaitTerminalEvent(2, TimeUnit.SECONDS)
//            this.assertError(Throwable("Error occurred"))
        //  }
//        val onSuccess = subscriber.events
//        val reviewResponse = onSuccess[0]
//
//        assertEquals((reviewResponse[0] as Throwable).message, viewModel.errorMessage.value!!.message)
    }
}