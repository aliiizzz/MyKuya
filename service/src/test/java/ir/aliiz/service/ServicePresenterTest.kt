package ir.aliiz.service

import androidx.lifecycle.Lifecycle
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import ir.aliiz.base.Loadable
import ir.aliiz.base.Schedulers
import ir.aliiz.data.repo.*
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit
import ir.aliiz.base.RxRule

class ServicePresenterTest {
    private val testScheduler = TestScheduler()

    @get:Rule
    val rxrule = RxRule(testScheduler)

    @RelaxedMockK
    lateinit var serviceRepo: ServiceRepo
    @RelaxedMockK
    lateinit var lifecycle: Lifecycle
    @RelaxedMockK
    lateinit var newsRepo: NewsRepo
    @RelaxedMockK
    lateinit var userRepo: UserRepo

    @RelaxedMockK
    lateinit var locationRepo: LocationRepo

    private val schedulers = Schedulers(testScheduler, testScheduler)

    fun createPresenter() = ServicePresenter(
        serviceRepo, newsRepo, userRepo, locationRepo, schedulers
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
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        val newsSlot = slot<Loadable<List<News>>>()
        verify {
            view.updateNews(capture(newsSlot))
        }

        assertEquals(1, (newsSlot.captured as Loadable.Loaded).data.count())
    }

    @Test
    fun `when presenter init called, make sure profile loaded`() {
        val user = User("1", "name", "last name")
        val view = mockk<ServicePresenter.ServiceView>(relaxed = true)
        every {
            userRepo.getProfile()
        } returns Observable.just(
            user
        )
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        val userSlot = slot<Loadable<User>>()
        verify {
            view.updateProfile(capture(userSlot))
        }

        assert(userSlot.captured is Loadable.Loaded)
        assertEquals(user, (userSlot.captured as Loadable.Loaded).data)
    }

    @Test
    fun `when presenter init called and profile failed, make sure profile has failed`() {
        val view = mockk<ServicePresenter.ServiceView>(relaxed = true)
        every {
            userRepo.getProfile()
        } returns Observable.error<User>(Exception("error"))
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        val userSlot = slot<Loadable<User>>()
        verify {
            view.updateProfile(capture(userSlot))
        }

        assert(userSlot.captured is Loadable.Failed)
        assertEquals("error", (userSlot.captured as Loadable.Failed).throwable.message)
    }

    @Test
    fun `when location updated, then city updated should called`() {
        val view = mockk<ServicePresenter.ServiceView>(relaxed = true)
        every {
            locationRepo.getCity(any())
        } returns Observable.just("tehran")
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        presenter.updateLocation(LatLng(1.0, 1.0))
        testScheduler.advanceTimeBy(2000, TimeUnit.MILLISECONDS)
        val citySlot = slot<String>()
        verify {
            view.updateCity(capture(citySlot))
        }

        assertEquals("tehran", citySlot.captured)
    }

    @Test
    fun `when location updated, then promotion updated should called`() {
        val promotions = listOf(
            Service("test", "test")
        )
        val view = mockk<ServicePresenter.ServiceView>(relaxed = true)
        every {
            serviceRepo.getFeaturedServices(any())
        } returns Observable.just(promotions)
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        presenter.updateLocation(LatLng(1.0, 1.0))
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        val promotionSlot = slot<Loadable<List<Service>>>()
        verify {
            view.promotedServices(capture(promotionSlot))
        }

        assert(promotionSlot.captured is Loadable.Loaded)
        assertEquals(promotions, (promotionSlot.captured as Loadable.Loaded).data)
    }
}