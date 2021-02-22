package us.azhimkulov.data.persistence.realm.executor

import io.reactivex.Observable
import us.azhimkulov.data.exception.ObjectNotFound
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import us.azhimkulov.data.persistence.realm.unit_of_work.factory.RealmUnitOfWorkFactory
import us.azhimkulov.data.persistence.realm.utils.Cloneable
import java.util.*

/**
 * Created by azamat  on 2/22/21.
 */
class RxRealmMultipleItemsQueryExecutorImpl(private val realmUnitOfWorkFactory: RealmUnitOfWorkFactory) :
    RxRealmMultipleItemsQueryExecutor {
    override fun <T : Cloneable<T>> executeQuery(performQuery: (RealmUnitOfWork) -> Collection<T?>?): Observable<Collection<T>> {
        return Observable.create{ emitter ->
            realmUnitOfWorkFactory.create().use { realmUnitOfWork ->
                val results = performQuery(realmUnitOfWork)

                val resultsCopy: MutableCollection<T> = ArrayList()
                results?.forEach { result ->
                    result?.let {
                        resultsCopy.add(it.makeShallowCopy())
                    }
                } ?: kotlin.run {
                    emitter.onError(ObjectNotFound())
                }
                emitter.onNext(resultsCopy)
                emitter.onComplete()
            }
        }
    }

}