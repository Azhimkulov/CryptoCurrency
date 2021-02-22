package us.azhimkulov.data.source

import io.reactivex.Completable
import io.reactivex.Observable
import us.azhimkulov.data.entity.CryptoEntity

/**
 * Created by azamat  on 2/22/21.
 */
interface CryptoDataStore {
    fun getCrypts(): Observable<Collection<CryptoEntity>>
    fun saveCrypts(collection: Collection<CryptoEntity>): Completable
}