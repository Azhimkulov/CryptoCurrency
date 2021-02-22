package us.azhimkulov.cryptocurrency.internal.di.module

import dagger.Module
import dagger.Provides
import us.azhimkulov.cryptocurrency.internal.di.PerActivity
import us.azhimkulov.data.entity.mapper.CryptoEntityDataMapper
import us.azhimkulov.data.repository.CryptoDataRepository
import us.azhimkulov.data.rest.RestClient
import us.azhimkulov.data.source.CryptoDataStoreFactory
import us.azhimkulov.domain.repository.CryptoRepository

/**
 * Created by azamat  on 2/21/21.
 */
@Module
class CryptoCollectionModule {

    @Provides
    @PerActivity
    fun provideCryptoRepository(
        storeFactory: CryptoDataStoreFactory,
        cryptoEntityDataMapper: CryptoEntityDataMapper
    ): CryptoRepository {
        return CryptoDataRepository(
            storeFactory, cryptoEntityDataMapper
        )
    }
}