package ir.aliiz.service

import androidx.lifecycle.Lifecycle
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import ir.aliiz.base.Loadable
import ir.aliiz.base.Schedulers
import ir.aliiz.data.repo.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.TimeUnit

class ServicePresenterTest {

    @RelaxedMockK
    lateinit var serviceRepo: ServiceRepo
    @RelaxedMockK
    lateinit var lifecycle: Lifecycle
    @RelaxedMockK
    lateinit var newsRepo: NewsRepo

    private val testScheduler = TestScheduler()
    private val schedulers = Schedulers(testScheduler, testScheduler)

    fun createPresenter() = ServicePresenter(
        serviceRepo, newsRepo, schedulers
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when get services called, then promotion and all services should loaded`() {
        val view = mockk<ServicePresenter.ServiceView>(relaxed = true)
        every {
            serviceRepo.getFeaturedServices(any())
        } returns Observable.just(
            listOf()
        )
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        presenter.init()
        presenter.updateLocation(LatLng(1.0, 1.0))
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        verify {
            view.promotedServices(Loadable.Loaded(listOf()))
        }
    }

    @Test
    fun `when presenter init called, make sure news loaded`() {
        val view = mockk<ServicePresenter.ServiceView>(relaxed = true)
        every {
            newsRepo.getNews()
        } returns Observable.just(
            listOf(
                News("1", "test", "test description", "banner")
            )
        )
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        presenter.init()
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        val newsSlot = slot<Loadable<List<News>>>()
        verify {
            view.updateNews(capture(newsSlot))
        }

        assertEquals(1, (newsSlot.captured as Loadable.Loaded).data.count())
    }
}