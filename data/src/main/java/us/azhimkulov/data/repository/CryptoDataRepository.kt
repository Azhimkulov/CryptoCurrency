package us.azhimkulov.data.repository

import android.util.Log
import io.reactivex.Observable
import us.azhimkulov.data.entity.mapper.CryptoEntityDataMapper
import us.azhimkulov.data.source.CryptoDataStoreFactory
import us.azhimkulov.data.source.CryptoRemoteDataStore
import us.azhimkulov.domain.model.CryptoModel
import us.azhimkulov.domain.repository.CryptoRepository
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoDataRepository @Inject constructor(
    private val dataStoreFactory: CryptoDataStoreFactory,
    private val cryptoEntityDataMapper: CryptoEntityDataMapper
) : CryptoRepository {

    override fun getCrypts(query: String?): Observable<Collection<CryptoModel>> {
        val dataStore = dataStoreFactory.retrieveDataStore()
        return dataStore.getCrypts()
            .flatMap {
                if (dataStore is CryptoRemoteDataStore) {
                    dataStoreFactory.retrieveLocaleDataStore().saveCrypts(it)
                        .andThen(Observable.just(it))
                } else {
                    Observable.just(it)
                }
            }
            .map { response ->
                return@map cryptoEntityDataMapper.transformCollection(response)
                    .filter { it.name.contains(query ?: "", true) }
            }
    }
}