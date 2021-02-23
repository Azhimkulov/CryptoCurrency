package us.azhimkulov.data

import us.azhimkulov.data.entity.CryptoEntity
import us.azhimkulov.domain.model.CryptoModel
import java.util.*

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoFactory {
    companion object {
        fun makeCollection(count: Int): Collection<CryptoEntity> {
            val collection = mutableListOf<CryptoEntity>()
            repeat(count) {
                collection.add(makeCrypto())
            }
            return collection
        }

        fun makeCrypto(): CryptoEntity {
            return CryptoEntity(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                Math.random(),
                UUID.randomUUID().toString(),
            )
        }

        fun makeModelCollection(count: Int): Collection<CryptoModel> {
            val collection = mutableListOf<CryptoModel>()
            repeat(count) {
                collection.add(makeCryptoModel())
            }
            return collection
        }

        fun makeCryptoModel(): CryptoModel {
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