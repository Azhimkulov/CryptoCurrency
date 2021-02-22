package us.azhimkulov.data.persistence.realm.repository.crypto

import us.azhimkulov.data.persistence.realm.entity.RealmCrypto

/**
 * Created by azamat  on 2/22/21.
 */
interface RealmCryptoRepository {
    fun get(): Collection<RealmCrypto>
    fun save(collection: Collection<RealmCrypto>)
    fun clearAll()
}