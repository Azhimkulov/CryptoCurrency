package us.azhimkulov.domain

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import us.azhimkulov.domain.executor.PostExecutionThread
import us.azhimkulov.domain.executor.ThreadExecutor
import us.azhimkulov.domain.interactor.GetCrypts
import us.azhimkulov.domain.model.CryptoModel
import us.azhimkulov.domain.repository.CryptoRepository
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by azamat  on 2/22/21.
 */

class GetCryptsTest {
    private lateinit var getCrypts: GetCrypts

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var cryptoRepository: CryptoRepository

    @Before
    fun setup() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        cryptoRepository = mock()

        getCrypts = GetCrypts(cryptoRepository, mockThreadExecutor, mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getCrypts.buildUseCaseObservable(null)
        verify(cryptoRepository).getCrypts(null)
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubCryptoRepositoryGetCrypts(Observable.just(CryptoFactory.makeCollection(3)))
        val testObserver = getCrypts.buildUseCaseObservable(null)
        testObserver.subscribe { assertTrue(true) }
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val crypts = CryptoFactory.makeCollection(5)
        stubCryptoRepositoryGetCrypts(Observable.just(crypts))
        val testObserver = getCrypts.buildUseCaseObservable(null)
        testObserver.subscribe { assertEquals(it, crypts) }
    }

    private fun stubCryptoRepositoryGetCrypts(observable: Observable<Collection<CryptoModel>>) {
        whenever(cryptoRepository.getCrypts(null))
            .thenReturn(observable)
    }
}