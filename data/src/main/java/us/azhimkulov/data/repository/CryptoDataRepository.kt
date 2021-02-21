package us.azhimkulov.data.repository

import io.reactivex.Observable
import us.azhimkulov.data.entity.mapper.CryptoEntityDataMapper
import us.azhimkulov.data.rest.RestClient
import us.azhimkulov.domain.model.CryptoModel
import us.azhimkulov.domain.repository.CryptoRepository
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoDataRepository @Inject constructor(
    private val restClient: RestClient,
    private val cryptoEntityDataMapper: CryptoEntityDataMapper
) : CryptoRepository {

    companion object {
        private const val SKIP_CONSTANT = 0
        private const val LIMIT_CONSTANT = 20
        private const val CURRENCY_CONSTANT = "EUR"
    }

    override fun getCrypts(): Observable<Collection<CryptoModel>> {
        return restClient.getCryptoApi()
            .getCoins(SKIP_CONSTANT, LIMIT_CONSTANT, CURRENCY_CONSTANT)
            .map {
                cryptoEntityDataMapper.transformCollection(it.coins)
            }
    }
}