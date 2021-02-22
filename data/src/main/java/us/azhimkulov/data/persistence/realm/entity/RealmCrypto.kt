package us.azhimkulov.data.persistence.realm.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import us.azhimkulov.data.persistence.realm.utils.Cloneable
import java.util.*

/**
 * Created by azamat  on 2/22/21.
 */
open class RealmCrypto : RealmObject(), Cloneable<RealmCrypto> {
    @PrimaryKey
    open var internalId: String = UUID.randomUUID().toString()
    open var externalId: String? = null
    open var icon: String? = null
    open var name: String? = null
    open var symbol: String? = null
    open var websiteUrl: String? = null
    open var price: Double = 0.0


    override fun makeShallowCopy(): RealmCrypto {
        val crypto = RealmCrypto()
        crypto.internalId = internalId
        crypto.externalId = externalId
        crypto.icon = icon
        crypto.name = name
        crypto.symbol = symbol
        crypto.websiteUrl = websiteUrl
        crypto.price = price
        return crypto
    }
}