package us.azhimkulov.data.source

import us.azhimkulov.data.cache.PreferencesHelper
import us.azhimkulov.data.persistence.realm.entity.RealmCrypto
import us.azhimkulov.data.persistence.realm.provider.RealmProvider
import javax.inject.Inject

/**
 * Created by azamat  on 2/22/21.
 */
class CryptoLocalDataBehaviourImpl @Inject constructor(
    private val realmProvider: RealmProvider,
    private val preferencesHelper: PreferencesHelper
) : CryptoLocalDataBehaviour {

    private val EXPIRATION_TIME = (10 * 1 * 1000).toLong()

    override fun setLastCacheTime() {
        val currentTime = System.currentTimeMillis()
        preferencesHelper.lastCacheTime = currentTime
    }

    override fun isCached(): Boolean {
        return realmProvider.provide().where(RealmCrypto::class.java).count() > 0
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.preferencesHelper.lastCacheTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }
}