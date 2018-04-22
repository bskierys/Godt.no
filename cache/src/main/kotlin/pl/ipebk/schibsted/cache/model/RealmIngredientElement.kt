package pl.ipebk.schibsted.cache.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmIngredientElement : RealmObject() {

  @PrimaryKey
  open var id: Long? = null

  open var amount: Float = 0f

  open var hint: String? = null

  open var name: String = ""

  open var unitName: String? = null

  open var symbol: String? = null
}