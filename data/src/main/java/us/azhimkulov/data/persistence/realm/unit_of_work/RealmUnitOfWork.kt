package us.azhimkulov.data.persistence.realm.unit_of_work

import us.azhimkulov.data.persistence.realm.repository.crypto.RealmCryptoRepository


/**
 * Created by azamat  on 2020-07-27.
 */
interface RealmUnitOfWork : AutoCloseable {
    fun getCrypts(): RealmCryptoRepository

    fun executeTransaction(execute: (RealmUnitOfWork) -> Unit)
}