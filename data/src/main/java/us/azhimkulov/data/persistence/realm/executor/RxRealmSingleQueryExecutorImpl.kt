package us.azhimkulov.data.persistence.realm.executor

import io.reactivex.Observable
import us.azhimkulov.data.exception.ObjectNotFound
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import us.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import us.azhimkulov.data.persistence.realm.utils.Cloneable
import javax.inject.Inject

/**
 * Created by azamat  on 2020-07-27.
 */
class RxRealmSingleQueryExecutorImpl @Inject constructor(
    private val realmUnitOfWorkFactory: RealmUnitOfWorkFactory
) : RxRealmSingleQueryExecutor {


    override fun <T : Cloneable<T>> executeQuery(performQuery: (RealmUnitOfWork) -> T?): Observable<T> {
        return Observable.create { emitter ->
            this.realmUnitOfWorkFactory.create().use { realmUnitOfWork ->
                val result = performQuery(realmUnitOfWork)

                if (result == null) {
                    emitter.onError(ObjectNotFound())
                    return@use
                }

                emitter.onNext(result.makeShallowCopy())
                emitter.onComplete()
            }
        }
    }
}