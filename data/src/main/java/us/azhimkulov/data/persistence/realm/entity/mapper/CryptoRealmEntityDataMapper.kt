package us.azhimkulov.data.persistence.realm.entity.mapper

import us.azhimkulov.data.entity.CryptoEntity
import us.azhimkulov.data.persistence.realm.entity.RealmCrypto
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoRealmEntityDataMapper @Inject constructor() :
    RealmEntityDataMapper<RealmCrypto, CryptoEntity>() {
    override fun transformToRealm(entity: CryptoEntity): RealmCrypto {
        val realmCrypto = RealmCrypto()
        realmCrypto.externalId = entity.id
        realmCrypto.icon = entity.icon
        realmCrypto.name = entity.name
        realmCrypto.symbol = entity.symbol
        realmCrypto.websiteUrl = entity.websiteUrl
        realmCrypto.price = entity.price
        return realmCrypto
    }

    override fun transformFromRealm(realmEntity: RealmCrypto): CryptoEntity {
        return CryptoEntity(
            realmEntity.externalId!!,
            realmEntity.icon,
            realmEntity.name!!,
            realmEntity.symbol!!,
            realmEntity.price,
            realmEntity.websiteUrl
        )
    }
}