package us.azhimkulov.data.entity.mapper

import us.azhimkulov.data.entity.CryptoEntity
import us.azhimkulov.domain.model.CryptoModel
import javax.inject.Inject

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoEntityDataMapper @Inject constructor() :
    BaseEntityDataMapper<CryptoModel, CryptoEntity>() {
    override fun transform(entity: CryptoEntity): CryptoModel {
        return CryptoModel(
            entity.id,
            entity.icon,
            entity.name,
            entity.symbol,
            entity.price,
            entity.websiteUrl ?: ""
        )
    }
}