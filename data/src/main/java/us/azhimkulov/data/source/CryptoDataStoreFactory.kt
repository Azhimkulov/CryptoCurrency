package us.azhimkulov.data.source

import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoDataStoreFactory @Inject constructor(
    private val cryptoLocalDataBehaviour: CryptoLocalDataBehaviour,
    private val cryptoRemoteDataStore: CryptoRemoteDataStore,
    private val cryptoLocalDataStore: CryptoLocalDataStore
) {

    fun retrieveDataStore(): CryptoDataStore {
        return if (cryptoLocalDataBehaviour.isCached() && !cryptoLocalDataBehaviour.isExpired() ) {
            retrieveLocaleDataStore()
        } else {
            retrieveRemoteDataStore()
        }
    }

    fun retrieveRemoteDataStore(): CryptoDataStore {
        return cryptoRemoteDataStore
    }

    fun retrieveLocaleDataStore(): CryptoDataStore {
        return cryptoLocalDataStore
    }
}