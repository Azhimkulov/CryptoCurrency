package us.azhimkulov.data.source

/**
 * Created by azamat  on 2/22/21.
 */
interface CryptoLocalDataBehaviour {
    fun setLastCacheTime()
    fun isCached(): Boolean
    fun isExpired(): Boolean
}