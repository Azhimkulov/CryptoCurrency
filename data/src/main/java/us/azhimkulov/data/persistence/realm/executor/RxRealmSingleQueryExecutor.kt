package us.azhimkulov.data.persistence.realm.executor

import io.reactivex.Observable
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import us.azhimkulov.data.persistence.realm.utils.Cloneable

/**
 * Created by azamat  on 2020-07-27.
 */
interface RxRealmSingleQueryExecutor {
    fun <T : Cloneable<T>> executeQuery(performQuery: (RealmUnitOfWork) -> T?): Observable<T>
}