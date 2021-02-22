package us.azhimkulov.cryptocurrency.model

/**
 * Created by azamat  on 2/21/21.
 */
class CryptoViewModel() {
    lateinit var id: String
    lateinit var icon: String
    lateinit var name: String
    lateinit var symbol: String
    lateinit var price: String
    lateinit var websiteUrl: String

    constructor(
        id: String,
        icon: String,
        name: String,
        symbol: String,
        price: Double,
        websiteUrl: String
    ) : this() {
        this.id = id
        this.icon = icon
        this.name = name
        this.symbol = symbol
        this.price = price.toString()
        this.websiteUrl = websiteUrl
    }
}