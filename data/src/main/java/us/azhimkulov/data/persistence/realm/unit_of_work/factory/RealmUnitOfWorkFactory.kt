package us.azhimkulov.data.persistence.realm.unit_of_work.factory

import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork

/**
 * Created by azamat  on 2020-07-27.
 */
interface RealmUnitOfWorkFactory {
    fun create(): RealmUnitOfWork
}