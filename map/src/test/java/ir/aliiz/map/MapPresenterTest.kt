package ir.aliiz.map

import androidx.lifecycle.Lifecycle
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.TestScheduler
import ir.aliiz.base.RxRule
import ir.aliiz.base.Schedulers
import ir.aliiz.data.repo.LatLng
import ir.aliiz.data.repo.LocationRepo
import org.junit.*

import java.util.concurrent.TimeUnit

class MapPresenterTest {

    private val testScheduler = TestScheduler()
    @get:Rule
    val rxRule= RxRule(testScheduler)

    @RelaxedMockK
    lateinit var locationRepo: LocationRepo

    @RelaxedMockK
    lateinit var lifecycle: Lifecycle

    private val schedulers = Schedulers(testScheduler, testScheduler)
    fun createPresenter() = MapPresenter(locationRepo, schedulers)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `when location updated and repo is success, then locationUpdated should called`() {
        every {
            locationRepo.updateLocation(any())
        } returns Completable.complete()
        val view= mockk<MapPresenter.MapView>(relaxed = true)
        val presenter = createPresenter()
        presenter.attachView(view, lifecycle)
        presenter.submitLocation(LatLng(1.0, 1.0))
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        verify {
            view.locationUpdated()
        }
    }

    @Test
    fun `when location updated and repo failed, then locationUpdatedError should called`() {
        every {
            locationRepo.updateLocation(any())
        } returns Completable.error(Exception("err"))

        val view= mockk<MapPresenter.MapView>(relaxed = true)
        val presenter = createPresenter()
        val errorSlot = slot<Throwable>()
        presenter.attachView(view, lifecycle)
        presenter.submitLocation(LatLng(1.0, 1.0))
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        verify {
            view.locationUpdateError(capture(errorSlot))
        }
        Assert.assertEquals("err", errorSlot.captured.message)
    }

}