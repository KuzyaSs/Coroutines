package ru.otus.coroutines.first

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import org.junit.Test
import ru.otus.coroutines.first.SomePresenter.Companion.GET_HEAVY_DATA_TIMEOUT

@OptIn(ExperimentalCoroutinesApi::class)
internal class SomePresenterTest {
    private lateinit var somePresenter: SomePresenter

    private lateinit var blockingRepository: BlockingRepository

    @OptIn(DelicateCoroutinesApi::class)
    private val uiDispatcher = newSingleThreadContext("UI thread")

    @Test
    fun `populate heavy data when get heavy data doesn't exceed timeout`() = runBlocking {
        // Arrange.
        blockingRepository = FakeBlockingRepository(timeout = GET_HEAVY_DATA_TIMEOUT - 1000)
        somePresenter = SomePresenter(
            blockingRepository = blockingRepository,
            mainDispatcher = uiDispatcher,
        )

        val expectedResult = Result.Success

        // Act.
        somePresenter.populateHeavyData().join()

        // Assert.
        val actualResult = somePresenter.result.value
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `populate heavy data when get heavy data exceeds timeout`() = runBlocking {
        // Arrange.
        blockingRepository = FakeBlockingRepository(timeout = GET_HEAVY_DATA_TIMEOUT + 1000)
        somePresenter = SomePresenter(
            blockingRepository = blockingRepository,
            mainDispatcher = uiDispatcher,
        )

        // Act.
        somePresenter.populateHeavyData().join()

        // Assert.
        val actualResult = somePresenter.result.value
        assertTrue(actualResult is Result.Error)
    }
}

internal class FakeBlockingRepository(
    private val timeout: Long,
) : BlockingRepository {
    override fun getHeavyData() {
        Thread.sleep(timeout)
    }
}
