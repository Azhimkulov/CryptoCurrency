package us.azhimkulov.data.persistence.realm.utils

import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork

/**
 * Created by azamat  on 2020-07-27.
 */
interface RealmUnitOfWorkTransactionAction {
    fun execute(realmUnitOfWork: RealmUnitOfWork)
}