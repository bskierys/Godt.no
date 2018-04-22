package pl.ipebk.schibsted.cache

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Class designed to simplify checking if data was already cached
 */
class IsCachedRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

  companion object {
    private const val KEY_WAS_CACHED = "key_was_cached"
  }

  fun setWasCached() {
    sharedPreferences.edit().putBoolean(KEY_WAS_CACHED, true).apply()
  }

  fun isCached(): Boolean {
    return sharedPreferences.getBoolean(KEY_WAS_CACHED, false)
  }
}