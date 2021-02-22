package us.azhimkulov.data.persistence.realm.unit_of_work

import io.realm.Realm
import us.azhimkulov.data.persistence.realm.repository.crypto.RealmCryptoRepository
import us.azhimkulov.data.persistence.realm.repository.crypto.RealmCryptoRepositoryImpl

class RealmUnitOfWorkImpl(
    private val realm: Realm
) : RealmUnitOfWork {

    private val realmCryptoRepository = RealmCryptoRepositoryImpl(realm)

    override fun getCrypts(): RealmCryptoRepository {
        return realmCryptoRepository
    }

    override fun executeTransaction(execute: (RealmUnitOfWork) -> Unit) {
        realm.executeTransaction {
            execute(this)
        }
    }

    override fun close() {
        realm.close()
    }

}