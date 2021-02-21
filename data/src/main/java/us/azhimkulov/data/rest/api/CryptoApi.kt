package us.azhimkulov.data.rest.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import us.azhimkulov.data.entity.CryptoEntity

/**
 * Created by azamat  on 2/21/21.
 */
interface CryptoApi {
    @GET("/coins")
    fun getCoins(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("currency") currency: String
    ): Observable<Collection<CryptoEntity>>
}