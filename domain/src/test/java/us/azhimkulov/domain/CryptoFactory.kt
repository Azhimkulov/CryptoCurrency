package us.azhimkulov.domain

import us.azhimkulov.domain.model.CryptoModel
import java.util.*

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoFactory {
    companion object {
        fun makeCollection(count: Int): Collection<CryptoModel> {
            val collection = mutableListOf<CryptoModel>()
            repeat(count) {
                collection.add(makeCrypto())
            }
            return collection
        }

        fun makeCrypto(): CryptoModel {
            return CryptoModel(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                Math.random(),
                UUID.randomUUID().toString(),
            )
        }
    }
}