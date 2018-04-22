package pl.ipebk.schibsted.cache.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmImage : RealmObject() {
  
  open var imboId: String? = null

  open var url: String? = null
}