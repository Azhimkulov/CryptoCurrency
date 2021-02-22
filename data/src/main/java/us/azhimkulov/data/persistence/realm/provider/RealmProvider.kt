package us.azhimkulov.data.persistence.realm.provider

import io.realm.Realm

/**
 * Created by azamat  on 2020-07-28.
 */
interface RealmProvider {
    fun provide(): Realm
}