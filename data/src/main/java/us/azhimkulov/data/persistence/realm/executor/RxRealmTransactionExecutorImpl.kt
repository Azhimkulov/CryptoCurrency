package us.azhimkulov.data.persistence.realm.executor

import io.reactivex.Completable
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import us.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import javax.inject.Inject

/**
 * Created by azamat  on 2020-07-27.
 */
class RxRealmTransactionExecutorImpl @Inject constructor(private val realmUnitOfWorkFactory: RealmUnitOfWorkFactory) :
    RxRealmTransactionExecutor {

    override fun executeTransaction(execute: (RealmUnitOfWork) -> Unit): Completable {
        return Completable.fromAction {
            this.realmUnitOfWorkFactory.create()
                .use { realmUnitOfWork -> realmUnitOfWork.executeTransaction(execute) }
        }
    }
}