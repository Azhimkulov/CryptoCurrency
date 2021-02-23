package us.azhimkulov.data

import android.util.Log
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import us.azhimkulov.data.entity.CryptoEntity
import us.azhimkulov.data.entity.mapper.CryptoEntityDataMapper
import us.azhimkulov.data.repository.CryptoDataRepository
import us.azhimkulov.data.source.CryptoDataStore
import us.azhimkulov.data.source.CryptoDataStoreFactory
import us.azhimkulov.data.source.CryptoLocalDataStore
import us.azhimkulov.data.source.CryptoRemoteDataStore
import us.azhimkulov.domain.model.CryptoModel

/**
 * Created by azamat  on 2/22/21.
 */
@RunWith(JUnit4::class)
class CryptoDataRepositoryTest {

    private lateinit var cryptoDataRepository: CryptoDataRepository

    private lateinit var cryptoDataStoreFactory: CryptoDataStoreFactory
    private lateinit var cryptoEntityDataMapper: CryptoEntityDataMapper
    private lateinit var cryptoLocalDataStore: CryptoLocalDataStore
    private lateinit var cryptoRemoteDataStore: CryptoRemoteDataStore

    @Before
    fun setup() {
        cryptoDataStoreFactory = mock()
        cryptoEntityDataMapper = mock()
        cryptoLocalDataStore = mock()
        cryptoRemoteDataStore = mock()
        cryptoDataRepository = CryptoDataRepository(cryptoDataStoreFactory, cryptoEntityDataMapper)
        stubCryptoDataStoreFactoryRetrieveLocalDataStore()
        stubCryptoDataStoreFactoryRetrieveRemoteDataStore()
    }

    @Test
    fun getCryptsCompletes() {
        stubCryptoDataStoreFactoryRetrieveDataStore(cryptoLocalDataStore)
        stubCryptoLocalDataStoreGetCrypts(Observable.just(CryptoFactory.makeCollection(2)))
        val testObserver = cryptoDataRepository.getCrypts(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCryptoReturnsData() {
        stubCryptoDataStoreFactoryRetrieveDataStore(cryptoLocalDataStore)
        val cryptoEntities = CryptoFactory.makeCollection(2).toList()
        val cryptoModels = CryptoFactory.makeModelCollection(2).toList()
        cryptoModels.forEachIndexed { index, cryptoModel ->
            stubCryptoMapperMapFromEntity(cryptoEntities[index], cryptoModel)
        }
        stubCryptoLocalDataStoreGetCrypts(Observable.just(cryptoEntities))

        val testObserver = cryptoDataRepository.getCrypts(null).test()
        testObserver.assertValue(cryptoModels)
    }

    private fun stubCryptoLocalDataStoreGetCrypts(observable: Observable<Collection<CryptoEntity>>) {
        whenever(cryptoLocalDataStore.getCrypts())
            .thenReturn(observable)
    }

    private fun stubCryptoDataStoreFactoryRetrieveLocalDataStore() {
        whenever(cryptoDataStoreFactory.retrieveLocaleDataStore())
            .thenReturn(cryptoLocalDataStore)
    }

    private fun stubCryptoDataStoreFactoryRetrieveRemoteDataStore() {
        whenever(cryptoDataStoreFactory.retrieveRemoteDataStore())
            .thenReturn(cryptoRemoteDataStore)
    }

    private fun stubCryptoDataStoreFactoryRetrieveDataStore(dataStore: CryptoDataStore) {
        whenever(cryptoDataStoreFactory.retrieveDataStore())
            .thenReturn(dataStore)
    }

    private fun stubCryptoMapperMapFromEntity(
        cryptoEntity: CryptoEntity,
        cryptoModel: CryptoModel
    ) {
        whenever(cryptoEntityDataMapper.transform(cryptoEntity))
            .thenReturn(cryptoModel)
    }
}