package us.azhimkulov.data.source

import io.reactivex.Completable
import io.reactivex.Observable
import us.azhimkulov.data.entity.CryptoEntity
import us.azhimkulov.data.rest.RestClient
import us.azhimkulov.data.utility.network.exception.parser.parseBadRequestWithMessage
import java.lang.UnsupportedOperationException
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoRemoteDataStore @Inject constructor(
    private val restClient: RestClient
) : CryptoDataStore {

    companion object {
        private const val SKIP_CONSTANT = 0
        private const val LIMIT_CONSTANT = 20
        private const val CURRENCY_CONSTANT = "EUR"
    }

    override fun getCrypts(): Observable<Collection<CryptoEntity>> {
        return restClient.getCryptoApi()
            .getCoins(SKIP_CONSTANT, LIMIT_CONSTANT, CURRENCY_CONSTANT)
            .map { it.coins }
            .onErrorResumeNext { error: Throwable ->
                Observable.error(parseBadRequestWithMessage(error))
            }
    }

    override fun saveCrypts(collection: Collection<CryptoEntity>): Completable {
        throw UnsupportedOperationException()
    }
}