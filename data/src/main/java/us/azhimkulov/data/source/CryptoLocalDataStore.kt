package us.azhimkulov.data.source

import io.reactivex.Completable
import io.reactivex.Observable
import us.azhimkulov.data.entity.CryptoEntity
import us.azhimkulov.data.persistence.realm.entity.RealmCrypto
import us.azhimkulov.data.persistence.realm.entity.mapper.CryptoRealmEntityDataMapper
import us.azhimkulov.data.persistence.realm.executor.RxRealmExecutorsProvider
import us.azhimkulov.data.persistence.realm.provider.RealmProvider
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoLocalDataStore @Inject constructor(
    private val cryptoLocalDataBehaviour: CryptoLocalDataBehaviour,
    private val rxRealmExecutorsProvider: RxRealmExecutorsProvider,
    private val cryptoRealmEntityDataMapper: CryptoRealmEntityDataMapper
) : CryptoDataStore {

    override fun getCrypts(): Observable<Collection<CryptoEntity>> {
        return rxRealmExecutorsProvider
            .provideMultipleItemsQueryExecutor()
            .executeQuery {
                return@executeQuery getRealmCrypts(it)
            }
            .map {
                cryptoRealmEntityDataMapper.transformRealmCollection(it)
            }
    }

    override fun saveCrypts(collection: Collection<CryptoEntity>): Completable {
        cryptoLocalDataBehaviour.setLastCacheTime()
        return rxRealmExecutorsProvider.provideTransactionExecutor().executeTransaction {
            val realmCollection = cryptoRealmEntityDataMapper.transformEntityCollection(collection)
            it.getCrypts().save(realmCollection)
        }
    }

    private fun getRealmCrypts(realmUnitOfWork: RealmUnitOfWork): Collection<RealmCrypto> {
        return realmUnitOfWork.getCrypts().get()
    }

}