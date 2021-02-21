package us.azhimkulov.data.rest

import us.azhimkulov.data.rest.api.CryptoApi

/**
 * Created by azamat  on 2/21/21.
 */
interface RestClient {
    fun getCryptoApi(): CryptoApi
}