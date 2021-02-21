package us.azhimkulov.domain.model

import java.math.RoundingMode
import java.text.DecimalFormat


/**
 * Created by azamat  on 2/21/21.
 */
class CryptoModel(
    val id: String,
    val icon: String?,
    val name: String,
    val symbol: String,
    val price: Double,
    val websiteUrl: String
) {
    fun getUserFriendlyPrice(): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_EVEN
        val formattedPrice = df.format(price).toString()
        return "$formattedPrice EUR"
    }
}
