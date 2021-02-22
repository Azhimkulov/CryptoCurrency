package us.azhimkulov.data.source

import io.reactivex.Observable
import us.azhimkulov.data.entity.CryptoEntity

/**
 * Created by azamat  on 2/22/21.
 */
interface CryptoDataStore {
    fun getCrypts(query: String): Observable<Collection<CryptoEntity>>
}