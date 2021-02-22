package us.azhimkulov.data.source

import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoDataStoreFactory @Inject constructor(
    private val cryptoRemoteDataStore: CryptoRemoteDataStore,
    private val cryptoLocalDataStore: CryptoLocalDataStore
) {

    fun retrieveDataStore(): CryptoDataStore {
        return retrieveRemoteDataStore()
    }

    private fun retrieveRemoteDataStore(): CryptoDataStore {
        return cryptoRemoteDataStore
    }

    fun retrieveLocaleDataStore(): CryptoDataStore {
        return cryptoLocalDataStore
    }
}