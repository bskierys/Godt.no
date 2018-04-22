package pl.ipebk.schibsted.cache

import io.realm.Realm

/**
 * Interface to avoid using Realm static methods
 */
interface RealmProvider {
  fun getRealm() : Realm
}