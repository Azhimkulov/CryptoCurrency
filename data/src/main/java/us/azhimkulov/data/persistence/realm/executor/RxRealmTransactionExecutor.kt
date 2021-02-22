package us.azhimkulov.data.persistence.realm.executor

import io.reactivex.Completable
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork

/**
 * Created by azamat  on 2020-07-27.
 */
interface RxRealmTransactionExecutor {
    fun executeTransaction(execute: (RealmUnitOfWork) -> Unit): Completable
}