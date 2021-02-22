package us.azhimkulov.data.persistence.realm.unit_of_work.factory

import us.azhimkulov.data.persistence.realm.provider.RealmProvider
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWork
import us.azhimkulov.data.persistence.realm.unit_of_work.RealmUnitOfWorkImpl

/**
 * Created by azamat  on 2020-07-28.
 */
class RealmUnitOfWorkFactoryImpl(private val realmProvider: RealmProvider) :
    RealmUnitOfWorkFactory {

    override fun create(): RealmUnitOfWork {
        return RealmUnitOfWorkImpl(this.realmProvider.provide())
    }
}