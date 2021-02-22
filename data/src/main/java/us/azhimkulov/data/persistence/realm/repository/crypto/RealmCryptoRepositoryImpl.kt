package us.azhimkulov.data.persistence.realm.repository.crypto

import io.realm.Realm
import us.azhimkulov.data.persistence.realm.entity.RealmCrypto

/**
 * Created by azamat  on 2/22/21.
 */
class RealmCryptoRepositoryImpl(private val realm: Realm) : RealmCryptoRepository {
    override fun get(): Collection<RealmCrypto> {
        return realm
            .where(RealmCrypto::class.java)
            .findAll()
    }

    override fun save(collection: Collection<RealmCrypto>) {
        clearAll()
        collection.forEach {
            realm.insertOrUpdate(it)
        }
    }

    override fun clearAll() {
        realm.delete(RealmCrypto::class.java)
    }
}