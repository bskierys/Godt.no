package pl.ipebk.schibsted.cache.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmStep : RealmObject() {

  @PrimaryKey
  open var id: Long? = null

  open var heading: String? = null

  open var description: String = ""
}