package us.azhimkulov.domain.repository

import io.reactivex.Observable
import us.azhimkulov.domain.model.CryptoModel

/**
 * Created by azamat  on 2/21/21.
 */
interface CryptoRepository {
    fun getCrypts(): Observable<Collection<CryptoModel>>
}