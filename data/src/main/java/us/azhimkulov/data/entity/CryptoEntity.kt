package us.azhimkulov.data.entity

/**
 * Created by azamat  on 2/21/21.
 */
data class CryptoEntity(
    val id: String,
    val icon: String?,
    val name: String,
    val symbol: String,
    val price: Double,
    val websiteUrl: String?
)
