package us.azhimkulov.data.cache

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by azamat  on 2/22/21.
 */
@Singleton
class PreferencesHelper @Inject constructor(context: Context) {
    companion object {
        private val PREF_CRYPTO_PREFERENCE_NAME = "us.azhimkulov.data.preference"
        private val KEY_LAST_CACHE = "LAST_CACHE_LONG_TIME"
        private val DEFAULT_LAST_CACHE = 0L
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_CRYPTO_PREFERENCE_NAME, Context.MODE_PRIVATE)

    var lastCacheTime: Long
        get() = sharedPreferences.getLong(KEY_LAST_CACHE, DEFAULT_LAST_CACHE)
        set(value) = sharedPreferences.edit().putLong(KEY_LAST_CACHE, value).apply()
}